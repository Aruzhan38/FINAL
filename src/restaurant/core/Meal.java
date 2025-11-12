package restaurant.core;

public interface Meal {
    String getName();
    double getPrice();

    default void accept(MealVisitor visitor) { visitor.visit(this); }
}
