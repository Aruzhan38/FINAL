package restaurant.decorator;

import restaurant.core.meal.Meal;

public class ExtraSeasons extends MealExtra{
    public ExtraSeasons(Meal base){
        super(base);
    }

    @Override
    public String getName() {
        return base.getName()+" + Extra Seasons";
    }

    @Override
    public int getPrice() {
        return base.getPrice()+150;
    }
}
