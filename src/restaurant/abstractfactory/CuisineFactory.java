package restaurant.abstractfactory;

import restaurant.core.drink.Drink;
import restaurant.core.meal.Meal;
import restaurant.core.side.Side;

public interface CuisineFactory {
    String cuisineName();
    Meal createMeal();
    Side createSide();
    Drink createDrink();
}
