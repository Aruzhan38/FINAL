package restaurant.builder;

import restaurant.core.*;
import restaurant.observer.Order;
import restaurant.strategy.PricingStrategy;

public class OrderBuilder {
    private Meal meal;
    private Side side;
    private Drink drink;
    private PricingStrategy pricingStrategy;

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

    public OrderBuilder setPricingStrategy(PricingStrategy strategy) {
        this.pricingStrategy = strategy;
        return this;
    }

    public Order build() {
        Order order = new Order(meal, side, drink);
        if (pricingStrategy != null) {
            order.setPricingStrategy(pricingStrategy);
        }
        return order;
    }
}
