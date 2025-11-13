package restaurant.decorator;

import restaurant.core.Meal;

public class ExtraSeasons extends MealExtra{
    public ExtraSeasons(Meal base){
        super(base);
    }

    @Override
    public String getName() {
        return super.getName()+"extra seasons";
    }

    @Override
    public double getPrice() {
        return super.getPrice()+150;
    }
}
