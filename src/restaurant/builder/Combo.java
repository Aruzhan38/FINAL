package restaurant.builder;

import restaurant.core.*;
import java.util.List;

public final class Combo {
    private final String name;
    private final Meal meal;
    private final Side side;
    private final Drink drink;
    private final List<String> notes;

    public Combo(String name, Meal meal, Side side, Drink drink, List<String> notes) {
        this.meal = meal;
        this.side = side;
        this.drink = drink;
        this.notes = notes == null ? List.of() : List.copyOf(notes);
        this.name = (name == null || name.isBlank())
                ? meal.name() + " + " + side.name() + " + " + drink.name()
                : name.strip();
    }

    public String name() { return name; }
    public Meal meal() { return meal; }
    public Side side() { return side; }
    public Drink drink() { return drink; }
    public List<String> notes() { return notes; }

    public int subtotal() {
        return meal.price() + side.price() + drink.price();
    }

    public String info() {
        return name + " — " + subtotal() + " ₸";
    }

    @Override
    public String toString() {
        return info();
    }
}

