package restaurant.decorator;

import restaurant.core.meal.Meal;

public class ExtraCheese extends MealExtra {
    public ExtraCheese(Meal base) {
        super(base);
    }

    @Override
    public String getName() {
        return base.getName() + " + Extra Cheese";
    }

    @Override
    public int getPrice() {
        return base.getPrice() + 300;
    }
}

