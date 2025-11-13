package restaurant.facade;

import restaurant.core.Drink;
import restaurant.core.Meal;
import restaurant.core.Side;
import restaurant.decorator.*;
import restaurant.factory.MealFactory;
import restaurant.factory.MealType;
import restaurant.observer.Order;
import restaurant.observer.OrderObserver;
import restaurant.observer.OrderStatus;
import restaurant.strategy.DiscountContext;
import restaurant.strategy.DiscountStrategy;
import java.util.List;

public class OrderFacade {
    public enum Extra { CHEESE, SPICY, HERBS, SEASONS }
    public Meal createBase(MealFactory factory, MealType type) {
        return factory.createMeal(type);
    }
    public Meal applyExtras(Meal base, boolean cheese, boolean spicy, boolean herbs, boolean seasons) {
        Meal meal = base;
        if (cheese)  meal = new ExtraCheese(meal);
        if (spicy)   meal = new SpicySauce(meal);
        if (herbs)   meal = new FreshHerbs(meal);
        if (seasons) meal = new ExtraSeasons(meal);
        return meal;
    }

    public Meal makeCombo(Meal current, Drink drink, Side side, double  discount, String label) {
        return new Combo(current, drink, side, discount, label);
    }


    public Order createOrder(String orderId, Meal meal, List<OrderObserver> observers) {
        Order order = new Order(orderId);
        if (observers != null) {
            for (OrderObserver o : observers) order.addObserver(o);
        }
        order.setStatus(OrderStatus.ACCEPTED);
        return order;
    }

    public PriceResult priceWithBestStrategy(Meal meal,
                                             List<DiscountStrategy> strategies,
                                             DiscountContext ctx) {
        double base = meal.getPrice();
        if (strategies == null || strategies.isEmpty()) {
            return new PriceResult(base, "NoDiscount");
        }
        double best = base;
        String applied = "NoDiscount";
        for (DiscountStrategy s : strategies) {
            s.collect(ctx);
            if (s.validate()) {
                double candidate = s.apply((int)Math.round(base));
                if (candidate < best) { best = candidate; applied = s.name(); }
            }
        }
        return new PriceResult(best, applied);
    }
    public FullOrder createComboOrder(String orderId,
                                      MealFactory factory, MealType type,
                                      List<Extra> extras,
                                      Drink drink, Side side, double comboDiscount, String comboLabel,
                                      List<OrderObserver> observers,
                                      List<DiscountStrategy> strategies,
                                      DiscountContext ctx) {

        Meal base  = createBase(factory, type);
        Meal withExtras = applyExtras(base, extras);
        Meal combo = makeCombo(withExtras, drink, side, comboDiscount, comboLabel);

        if (ctx != null) ctx.meal = combo;

        PriceResult priced = priceWithBestStrategy(combo, strategies, ctx);
        Order order = createOrder(orderId, combo, observers);

        return new FullOrder(order, combo, priced.finalPrice, priced.appliedStrategy);
    }

    private Meal applyExtras(Meal base, List<Extra> extras) {

    }

    public static class PriceResult {
        public final double finalPrice;
        public final String appliedStrategy;
        public PriceResult(double p, String s) { this.finalPrice = p; this.appliedStrategy = s; }
    }

    public static class FullOrder {
        public final Order order;
        public final Meal meal;
        public final double finalPrice;
        public final String strategy;
        public FullOrder(Order o, Meal m, double p, String s) {
            this.order=o; this.meal=m; this.finalPrice=p; this.strategy=s;
        }
    }
}
