package restaurant.abstractfactory;

import restaurant.core.drink.*;
import restaurant.core.meal.*;
import restaurant.core.side.*;

public class Turkish implements CuisineFactory {

    @Override
    public String cuisineName() {
        return "Turkish Cuisine";
    }
    @Override
    public Meal createMeal() {
        return new IskenderKebab();
    }
    @Override
    public Side createSide()  {
        return new Pilav();
    }
    @Override
    public Drink createDrink(){
        return new Ayran();
    }

}
