package restaurant.builder;

import restaurant.abstractfactory.CuisineFactory;
import restaurant.core.*;
import java.util.ArrayList;
import java.util.List;

public final class ComboBuilder {
    private String name;
    private Meal meal;
    private Side side;
    private Drink drink;
    private final List<String> notes = new ArrayList<>();

    public static ComboBuilder fromCuisine(CuisineFactory factory) {
        return new ComboBuilder()
                .meal(factory.meal())
                .side(factory.side())
                .drink(factory.drink())
                .name(factory.cuisineName() + " Combo");
    }

    public ComboBuilder name(String name) {
        this.name = name;
        return this;
    }
    public ComboBuilder meal(Meal meal) {
        this.meal = meal;
        return this;
    }
    public ComboBuilder side(Side side) {
        this.side = side;
        return this;
    }
    public ComboBuilder drink(Drink drink) {
        this.drink = drink;
        return this;
    }

    public ComboBuilder note(String note) {
        if (note != null && !note.isBlank()) notes.add(note.strip());
        return this;
    }

    public Combo build() {
        return new Combo(name, meal, side, drink, notes);
    }
}
