package restaurant.builder;

import restaurant.core.*;
import restaurant.observer.Order;

public class OrderBuilder {

    private String id;
    private Meal meal;
    private Side side;
    private Drink drink;

    public OrderBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public OrderBuilder setMeal(Meal meal) {
        this.meal = meal;
        return this;
    }

    public OrderBuilder setSide(Side side) {
        this.side = side;
        return this;
    }

    public OrderBuilder setDrink(Drink drink) {
        this.drink = drink;
        return this;
    }

    public Order build() {
        if (id == null || id.isBlank()) {
            throw new IllegalStateException("Order id must be set before build()");
        }
        return new Order(id, meal, side, drink);
    }
}
