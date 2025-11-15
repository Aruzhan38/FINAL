package restaurant.decorator;

import restaurant.core.Meal;

public class FreshHerbs extends MealExtra{
    public FreshHerbs(Meal base){
        super(base);
    }

    @Override
    public String getName() {
        return base.getName()+" Fresh Herbs";
    }

    @Override
    public double getPrice() {
        return base.getPrice()+100;
    }
}
