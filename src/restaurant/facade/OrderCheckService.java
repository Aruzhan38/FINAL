package restaurant.facade;

import restaurant.core.drink.Drink;
import restaurant.core.meal.Meal;
import restaurant.core.side.Side;
import restaurant.observer.Order;

public class OrderCheckService {
    // check
    void printSummary(Order order,
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
