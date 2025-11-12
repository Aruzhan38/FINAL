package restaurant.decorator;

import restaurant.core.Meal;

public abstract class FreshHerbs extends MealExtra{
    public FreshHerbs(Meal base){
        super(base);
    }

    @Override
    public String name() {
        return super.name()+" fresh herbs";
    }

    @Override
    public int price() {
        return super.price()+100;
    }
}
