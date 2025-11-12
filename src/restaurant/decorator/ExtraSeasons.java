package restaurant.decorator;

import restaurant.core.Meal;

public abstract class ExtraSeasons extends MealExtra{
    public ExtraSeasons(Meal base){
        super(base);
    }

    @Override
    public String name() {
        return super.name()+"extra seasons";
    }

    @Override
    public int price() {
        return super.price()+150;
    }
}
