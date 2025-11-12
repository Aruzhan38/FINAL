package restaurant.core;

public interface MealVisitor {
    void visit(Meal meal);
    void visit(Side side);
    void visit(Drink drink);
}
