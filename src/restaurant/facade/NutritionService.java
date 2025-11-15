package restaurant.facade;

import restaurant.core.drink.Drink;
import restaurant.core.meal.Meal;
import restaurant.core.side.Side;
import restaurant.visitor.NutritionVisitor;

public class NutritionService {
    int calcCalories(Meal meal, Side side, Drink drink) {
        NutritionVisitor visitor = new NutritionVisitor();
        if (meal != null) meal.accept(visitor);
        if (side != null) side.accept(visitor);
        if (drink != null) drink.accept(visitor);
        return visitor.getTotalKcal();
    }
}
