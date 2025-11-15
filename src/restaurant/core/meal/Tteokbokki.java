package restaurant.core.meal;

public final class Tteokbokki implements Meal {

    @Override
    public String getName()  {
        return "Tteokbokki";
    }

    @Override
    public int getPrice() {
        return 2800;
    }
}
