package restaurant.core;

import restaurant.core.drink.Drink;
import restaurant.core.meal.Meal;
import restaurant.core.side.Side;

public interface MealVisitor {
    void visit(Meal meal);
    void visit(Side side);
    void visit(Drink drink);
}
