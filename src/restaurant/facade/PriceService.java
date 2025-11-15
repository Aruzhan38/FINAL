package restaurant.facade;

import restaurant.core.drink.Drink;
import restaurant.core.meal.Meal;
import restaurant.core.side.Side;

public class PriceService {
    double calcBasePrice(Meal meal, Side side, Drink drink) {
        int total = 0;
        if (meal != null) total += meal.getPrice();
        if (side != null) total += side.getPrice();
        if (drink != null) total += drink.getPrice();
        return total;
    }
}
