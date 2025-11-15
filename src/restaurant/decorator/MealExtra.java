package restaurant.decorator;

import restaurant.core.*;
import restaurant.core.meal.Meal;

public abstract class MealExtra implements Meal {
    protected final Meal base;

    protected MealExtra(Meal base) {
        if (base == null) throw new IllegalArgumentException("base meal is null");
        this.base = base;
    }

    @Override
    public String getName() {
        return base.getName();
    }

    @Override
    public int getPrice() {
        return base.getPrice();
    }

    @Override
    public void accept(MealVisitor visitor) {
        base.accept(visitor);
        visitor.visit(this);
    }
}
