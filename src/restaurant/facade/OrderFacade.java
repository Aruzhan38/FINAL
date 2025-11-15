package restaurant.facade;

import restaurant.abstractfactory.CuisineFactory;
import restaurant.builder.OrderBuilder;
import restaurant.core.drink.Drink;
import restaurant.core.meal.Meal;
import restaurant.core.side.Side;
import restaurant.decorator.*;
import restaurant.observer.*;
import restaurant.strategy.*;

public class OrderFacade {

    private final OrderIdService idService;
    private final PriceService priceService;
    private final NutritionService nutritionService;
    private final OrderCheckService checkService;

    public OrderFacade() {
        this.idService = new OrderIdService();
        this.priceService = new PriceService();
        this.nutritionService = new NutritionService();
        this.checkService = new OrderCheckService();
    }
    // creating an order
    public Order createOrder(CuisineFactory factory,
                             boolean extraCheese,
                             boolean extraSeasons,
                             boolean spicySauce,
                             boolean freshHerbs,
                             DiscountStrategy discount,
                             DiscountContext ctx,
                             String customerName) {

        String id = idService.nextOrderId();
        // creation of base products using Abstract Factory
        Meal meal = factory.createMeal();
        Side side = factory.createSide();
        Drink drink = factory.createDrink();
        // wrapping meal with decorators - extras
        if (spicySauce) meal = new SpicySauce(meal);
        if (extraSeasons) meal = new ExtraSeasons(meal);
        if (extraCheese) meal = new ExtraCheese(meal);
        if (freshHerbs) meal = new FreshHerbs(meal);
        // building order by using Builder
        Order order = new OrderBuilder()
                .setId(id)
                .build();

        attachObservers(order, customerName, "MainLine");

        int basePrice = (int) priceService.calcBasePrice(meal, side, drink);

        int finalPrice = basePrice;
        String discountName = "No discount";
        // applying discount
        if (discount != null) {
            discount.collect(ctx);
            if (discount.validate()) {
                finalPrice = discount.apply(basePrice);
            }
            discountName = discount.name();
        }
        // calculating calories using Visitor
        int kcal = nutritionService.calcCalories(meal, side, drink);

        checkService.printSummary(order, meal, side, drink, basePrice, finalPrice, discountName, kcal);

        return order;
    }

    private void attachObservers(Order order, String customerName, String kitchenName) {
        OrderObserver kitchen  = new KitchenDisplay(kitchenName);
        OrderObserver customer = new CustomerApp(customerName);
        order.addObserver(kitchen);
        order.addObserver(customer);
    }
    // updating order status and notification all observers about the change
    public void updateStatus(Order order, OrderStatus newStatus) {
        order.setStatus(newStatus);
    }
}
