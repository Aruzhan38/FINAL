package restaurant.factory;

import restaurant.core.Meal;

public final class IskenderKebab implements Meal {

    @Override
    public String getName()  {
        return "Iskenser-Kebab";
    }

    @Override
    public double getPrice() {
        return 3900;
    }
}
