package restaurant.core.meal;

public final class IskenderKebab implements Meal {

    @Override
    public String getName()  {
        return "Iskenser-Kebab";
    }

    @Override
    public int getPrice() {
        return 3900;
    }
}
