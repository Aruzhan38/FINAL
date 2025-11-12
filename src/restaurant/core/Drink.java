package restaurant.core;

public interface Drink {
    String getName();
    double getPrice();

    default void accept(MealVisitor visitor) { visitor.visit(this); }
}