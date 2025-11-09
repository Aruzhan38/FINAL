package restaurant.factory;

import restaurant.core.Meal;
import restaurant.core.MealVisitor;
import java.util.Set;

public final class Manty implements Meal {

    @Override
    public String name()  {
        return "Manty";
    }

    @Override
    public int price() {
        return 3200;
    }

    @Override
    public void accept(MealVisitor v) {
        v.visitBase(name(), price(), 650, Set.of("GLUTEN"));
    }
}
