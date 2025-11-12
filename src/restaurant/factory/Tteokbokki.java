package restaurant.factory;

import restaurant.core.Meal;

public final class Tteokbokki implements Meal {

    @Override
    public String getName()  {
        return "Tteokbokki";
    }

    @Override
    public double getPrice() {
        return 2800;
    }
}
