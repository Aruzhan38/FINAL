package restaurant.core;

public interface Side {
    String getName();
    double getPrice();

    default void accept(MealVisitor visitor) { visitor.visit(this); }
}
