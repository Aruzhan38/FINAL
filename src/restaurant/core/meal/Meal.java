package restaurant.core.meal;

import restaurant.core.MealVisitor;

public interface Meal {
    String getName();
    int getPrice();

    default void accept(MealVisitor visitor) { visitor.visit(this); }
}
