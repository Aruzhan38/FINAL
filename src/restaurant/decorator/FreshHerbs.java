package restaurant.decorator;

import restaurant.core.meal.Meal;

public class FreshHerbs extends MealExtra{
    public FreshHerbs(Meal base){
        super(base);
    }

    @Override
    public String getName() {
        return base.getName()+" + Fresh Herbs";
    }

    @Override
    public int getPrice() {
        return base.getPrice()+100;
    }
}
