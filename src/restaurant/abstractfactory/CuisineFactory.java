package restaurant.abstractfactory;

import restaurant.core.*;

public interface CuisineFactory {
    String cuisineName();
    Meal  createMeal();
    Side  createSide();
    Drink createDrink();
}
