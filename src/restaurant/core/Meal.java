package restaurant.core;

public interface Meal extends Named {
    int price();
    void accept(MealVisitor visitor);
}
