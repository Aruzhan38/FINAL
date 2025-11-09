package restaurant.factory;

import restaurant.core.Meal;
import restaurant.core.MealVisitor;
import java.util.Set;

public final class IskenderKebab implements Meal {

    @Override
    public String name()  {
        return "Iskenser-Kebab";
    }

    @Override
    public int price() {
        return 3900;
    }

    @Override
    public void accept(MealVisitor v) {
        v.visitBase(name(), price(), 720, Set.of("GLUTEN", "DAIRY"));
    }
}
