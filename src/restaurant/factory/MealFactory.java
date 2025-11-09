package restaurant.factory;

import restaurant.core.Meal;

public final class MealFactory {
    private MealFactory() {}

    public static Meal create(MealType type) {
        return switch (type) {
            case MANTY_KZ          -> new Manty();
            case ISKENDER_KEBAB_TR -> new IskenderKebab();
            case TTEOKBOKKI_KR     -> new Tteokbokki();
        };
    }

    public static Meal create(String code) {
        return create(MealType.valueOf(code.trim().toUpperCase()));
    }
}
