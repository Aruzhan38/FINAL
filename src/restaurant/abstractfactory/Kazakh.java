package restaurant.abstractfactory;

import restaurant.core.drink.*;
import restaurant.core.meal.*;
import restaurant.core.side.*;

public class Kazakh implements CuisineFactory {

    @Override
    public String cuisineName() {
        return "Kazakh Cuisine";
    }
    @Override
    public Meal createMeal() {
        return new Manty();
    }
    @Override
    public Side createSide()  {
        return new Baursak();
    }
    @Override
    public Drink createDrink(){
        return new Kymyz();
    }
}
