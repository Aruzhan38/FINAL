package restaurant.factory;

import restaurant.core.Meal;

public final class MealFactory {
    private MealFactory() {}

    public static Meal create(MealType type) {
        switch (type) {
            case MANTY:           return new Manty();
            case ISKENDER_KEBAB:  return new IskenderKebab();
            case TTEOKBOKKI:      return new Tteokbokki();
            default:
                throw new IllegalArgumentException("Unknown meal type: " + type);
        }
    }

    public static Meal create(String name) {
        if (name == null)
            throw new IllegalArgumentException("Name is null");
        switch (name.trim().toLowerCase()) {
            case "manty":            return new Manty();
            case "iskender":         return new IskenderKebab();
            case "iskender kebab":   return new IskenderKebab();
            case "tteokbokki":       return new Tteokbokki();
            default:
                throw new IllegalArgumentException("Unknown meal name: " + name);
        }
    }
}
