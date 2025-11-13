package restaurant.decorator;

import restaurant.core.Meal;

public class FreshHerbs extends MealExtra{
    public FreshHerbs(Meal base){
        super(base);
    }

    @Override
    public String getName() {
        return super.getName()+" fresh herbs";
    }

    @Override
    public double getPrice() {
        return super.getPrice()+100;
    }
}
