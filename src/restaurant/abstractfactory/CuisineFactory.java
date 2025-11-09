package restaurant.abstractfactory;

import restaurant.core.*;

public interface CuisineFactory {
    String cuisineName();
    Meal  meal();
    Side  side();
    Drink drink();
}
