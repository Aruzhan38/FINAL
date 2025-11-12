package restaurant.decorator;

import restaurant.core.Meal;

public abstract class MealExtra implements Meal {
    protected final Meal base;

    protected MealExtra(Meal base){
        this.base=base;
    }
    @Override
    public String name() {
        return base.name();
    }

    @Override
    public int price() {
        return base.price();
    }

}
