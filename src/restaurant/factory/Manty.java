package restaurant.factory;

import restaurant.core.Meal;

public final class Manty implements Meal {

    @Override
    public String getName()  {
        return "Manty";
    }

    @Override
    public double getPrice() {
        return 2200;
    }
}
