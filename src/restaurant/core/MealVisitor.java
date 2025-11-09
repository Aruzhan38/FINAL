package restaurant.core;

import java.util.Set;

public interface MealVisitor {
    void visitBase(String baseName, int basePrice, int baseCalories, Set<String> baseAllergens);
    void visitExtra(String extraName, int extraPrice, int extraCalories, Set<String> extraAllergens);
    default void end() {}
}
