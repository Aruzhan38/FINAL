package restaurant.core.side;

import restaurant.core.MealVisitor;

public interface Side {
    String getName();
    int getPrice();

    default void accept(MealVisitor visitor) { visitor.visit(this); }
}
