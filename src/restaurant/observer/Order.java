package restaurant.observer;

import restaurant.core.*;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final String id;

    private final Meal meal;
    private final Side side;
    private final Drink drink;

    private OrderStatus status = OrderStatus.CREATED;
    private final List<OrderObserver> observers = new ArrayList<>();

    public Order(String id) {
        this(id, null, null, null);
    }

    public Order(String id, Meal meal, Side side, Drink drink) {
        this.id = id;
        this.meal = meal;
        this.side = side;
        this.drink = drink;
    }

    public String getId()    { return id; }
    public Meal  getMeal()   { return meal; }
    public Side  getSide()   { return side; }
    public Drink getDrink()  { return drink; }

    public OrderStatus getStatus() { return status; }

    public void addObserver(OrderObserver o) { observers.add(o); }
    public void removeObserver(OrderObserver o) { observers.remove(o); }

    public void setStatus(OrderStatus newStatus) {
        if (this.status == newStatus) return;
        this.status = newStatus;
        for (OrderObserver o : observers) {
            o.onStatusChanged(id, newStatus);
        }
    }
}
