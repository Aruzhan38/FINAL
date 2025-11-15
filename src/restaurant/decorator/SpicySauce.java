package restaurant.decorator;

import restaurant.core.meal.Meal;

public  class SpicySauce extends MealExtra {
    public SpicySauce(Meal base){
        super(base);
    }

    @Override
    public String getName() {
        return base.getName()+" + Spicy Sauce";
    }

    @Override
    public int getPrice() {
        return base.getPrice()+100;
    }
}
