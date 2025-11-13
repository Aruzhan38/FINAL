package restaurant.decorator;

import restaurant.core.Meal;

public class ExtraCheese extends MealExtra{
    public ExtraCheese(Meal base){
        super(base);
    }

    @Override
    public String getName() {
        return super.getName()+" extra cheese";
    }

    @Override
    public double getPrice() {
        return super.getPrice()+300;
    }
}
