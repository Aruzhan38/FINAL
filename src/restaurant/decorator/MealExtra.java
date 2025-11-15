package restaurant.decorator;

import restaurant.core.*;

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
    public double getPrice() {
        return base.getPrice();
    }

    @Override
    public void accept(MealVisitor visitor) {
        visitor.visit(this);
        base.accept(visitor);
    }
}
