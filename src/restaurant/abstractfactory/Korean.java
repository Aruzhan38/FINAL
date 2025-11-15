package restaurant.abstractfactory;

import restaurant.core.drink.*;
import restaurant.core.meal.*;
import restaurant.core.side.*;

public class Korean implements CuisineFactory {

    @Override
    public String cuisineName() {
        return "Korean Cuisine";
    }
    @Override
    public Meal createMeal() {
        return new Tteokbokki();
    }
    @Override
    public Side createSide()  {
        return new Kimchi();
    }
    @Override
    public Drink createDrink(){
        return new BarleyTea();
    }
}
