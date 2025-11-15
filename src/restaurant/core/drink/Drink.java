package restaurant.core.drink;

import restaurant.core.MealVisitor;

public interface Drink {
    String getName();
    int getPrice();

    default void accept(MealVisitor visitor) { visitor.visit(this); }
}