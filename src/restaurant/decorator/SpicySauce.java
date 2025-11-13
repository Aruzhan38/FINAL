package restaurant.decorator;

import restaurant.core.Meal;

public class SpicySauce extends MealExtra{
    public SpicySauce(Meal base){
        super(base);
    }

    @Override
    public String getName() {
        return super.getName()+" spicy sauce";
    }

    @Override
    public double getPrice() {
        return super.getPrice()+100;
    }
}
