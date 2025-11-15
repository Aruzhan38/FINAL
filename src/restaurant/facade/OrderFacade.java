package restaurant.facade;

import restaurant.abstractfactory.CuisineFactory;
import restaurant.builder.OrderBuilder;
import restaurant.core.drink.Drink;
import restaurant.core.meal.Meal;
import restaurant.core.side.Side;
import restaurant.decorator.*;
import restaurant.observer.*;
import restaurant.strategy.*;
import restaurant.visitor.NutritionVisitor;

public class OrderFacade {

    private static int counter = 1;
    private static String nextOrderId() {
        return "ORDER-" + (counter++);
    }

    public Order createOrder(CuisineFactory factory,
                             boolean extraCheese,
                             boolean extraSeasons,
                             boolean spicySauce,
                             boolean freshHerbs,
                             DiscountStrategy discount,
                             DiscountContext ctx,
                             String customerName) {

        String id = nextOrderId();

        Meal meal = factory.createMeal();
        Side side = factory.createSide();
        Drink drink = factory.createDrink();

        if (spicySauce) meal = new SpicySauce(meal);
        if (extraSeasons) meal = new ExtraSeasons(meal);
        if (extraCheese) meal = new ExtraCheese(meal);
        if (freshHerbs) meal = new FreshHerbs(meal);

        Order order = new OrderBuilder()
                .setId(id)
                .build();
        attachObservers(order, customerName, "MainLine");

        int basePrice = (int) calcBasePrice(meal, side, drink);

        int finalPrice = basePrice;
        String discountName = "No discount";

        if (discount != null) {
            discount.collect(ctx);
            if (discount.validate()) {
                finalPrice = discount.apply(basePrice);
            }
            discountName = discount.name();
        }

        int kcal = calcCalories(meal, side, drink);

        printSummary(order, meal, side, drink, basePrice, finalPrice, discountName, kcal);

        return order;
    }

    private void attachObservers(Order order, String customerName, String kitchenName) {
        OrderObserver kitchen  = new KitchenDisplay(kitchenName);
        OrderObserver customer = new CustomerApp(customerName);
        order.addObserver(kitchen);
        order.addObserver(customer);
    }

    public void updateStatus(Order order, OrderStatus newStatus) {
        order.setStatus(newStatus);
    }

    private double calcBasePrice(Meal meal, Side side, Drink drink) {
        int total = 0;
        if (meal != null) total += meal.getPrice();
        if (side != null) total += side.getPrice();
        if (drink != null) total += drink.getPrice();
        return total;
    }

    private int calcCalories(Meal meal, Side side, Drink drink) {
        NutritionVisitor visitor = new NutritionVisitor();
        if (meal != null) meal.accept(visitor);
        if (side != null) side.accept(visitor);
        if (drink != null) drink.accept(visitor);
        return visitor.getTotalKcal();
    }

    private void printSummary(Order order,
                              Meal meal,
                              Side side,
                              Drink drink,
                              int basePrice,
                              int finalPrice,
                              String discountName,
                              int kcal) {

        System.out.println("==============================================");
        System.out.println("Order ID: " + order.getId());
        System.out.println("Type    : SET");

        if (meal != null) System.out.println("Meal   : " + meal.getName());
        if (side != null) System.out.println("Side   : " + side.getName());
        if (drink != null) System.out.println("Drink  : " + drink.getName());

        System.out.println("----------------------------------------------");
        System.out.println("Base price : " + basePrice);
        System.out.println("Discount   : " + discountName);
        System.out.println("Final price: " + finalPrice);
        System.out.println("Calories   : " + kcal + " kcal");
        System.out.println("Status     : " + order.getStatus());
        System.out.println("==============================================");
    }
}
