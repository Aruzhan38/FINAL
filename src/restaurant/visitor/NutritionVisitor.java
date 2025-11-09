package restaurant.visitor;

import restaurant.core.MealVisitor;
import java.util.HashSet;
import java.util.Set;

public final class NutritionVisitor implements MealVisitor {
    private int calories = 0;
    private final Set<String> allergens = new HashSet<>();

    @Override
    public void visitBase(String baseName, int basePrice, int baseCalories, Set<String> baseAllergens) {
        calories += baseCalories;
        if (baseAllergens != null) allergens.addAll(baseAllergens);
    }

    @Override
    public void visitExtra(String extraName, int extraPrice, int extraCalories, Set<String> extraAllergens) {
        calories += extraCalories;
        if (extraAllergens != null) allergens.addAll(extraAllergens);
    }

    @Override
    public void end() { }

    public int getCalories() {
        return calories;
    }

    public Set<String> getAllergens() {
        return Set.copyOf(allergens);
    }
}
