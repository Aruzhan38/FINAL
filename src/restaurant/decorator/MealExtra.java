package restaurant.decorator;

import restaurant.core.Meal;

public abstract class MealExtra implements Meal {
    protected final Meal base;

    protected MealExtra(Meal base){
        this.base=base;
    }

    @Override
    public String getName() {
        return base.getName();
    }

    @Override
    public double getPrice() {
        return base.getPrice();
    }

}
