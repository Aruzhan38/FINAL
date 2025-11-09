package restaurant.factory;

import restaurant.core.Meal;
import restaurant.core.MealVisitor;
import java.util.Set;

public final class Tteokbokki implements Meal {

    @Override
    public String name()  {
        return "Tteokbokki";
    }

    @Override
    public int price() {
        return 2800;
    }

    @Override
    public void accept(MealVisitor v) {
        v.visitBase(name(), price(), 560, Set.of("SOY"));
    }
}
