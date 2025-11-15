package restaurant.decorator;

import restaurant.core.Meal;

public  class SpicySauce extends MealExtra {
    public SpicySauce(Meal base){
        super(base);
    }

    @Override
    public String getName() {
        return base.getName()+" Spicy Sauce";
    }

    @Override
    public double getPrice() {
        return base.getPrice()+100;
    }
}
