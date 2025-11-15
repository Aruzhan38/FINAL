package restaurant.decorator;

import restaurant.core.Meal;

public class ExtraCheese extends MealExtra {
    public ExtraCheese(Meal base) {
        super(base);
    }

    @Override
    public String getName() {
        return base.getName() + " + Extra Cheese";
    }

    @Override
    public double getPrice() {
        return base.getPrice() + 300;
    }
}

