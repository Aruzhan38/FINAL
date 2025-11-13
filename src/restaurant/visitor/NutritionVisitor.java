package restaurant.visitor;

import restaurant.core.*;
import restaurant.core.MealVisitor;
import java.util.HashMap;
import java.util.Map;

public class NutritionVisitor implements MealVisitor {
    private final Map<String, Integer> kcalByName = new HashMap<>();
    private int totalKcal = 0;

    public NutritionVisitor() {

        kcalByName.put("Manty", 420);
        kcalByName.put("Iskender Kebab", 650);
        kcalByName.put("Tteokbokki", 380);

        kcalByName.put("Baursak", 250);
        kcalByName.put("Pilav", 270);
        kcalByName.put("Kimchi", 40);

        kcalByName.put("Kymyz", 120);
        kcalByName.put("Ayran", 90);
        kcalByName.put("Barley Tea", 5);

        kcalByName.put("Spicy Sauce", 20);
        kcalByName.put("Extra Cheese", 110);
        kcalByName.put("Extra Meat", 180);
        kcalByName.put("Fresh Herbs", 5);
    }

    public NutritionVisitor with(String name, int kcal) {
        kcalByName.put(name, kcal);
        return this;
    }

    @Override public void visit(Meal meal)  { add(meal.getName()); }
    @Override public void visit(Side side)  { add(side.getName()); }
    @Override public void visit(Drink drink){ add(drink.getName()); }

    private void add(String name) {
        totalKcal += kcalByName.getOrDefault(name, 0);
    }

    public int getTotalKcal() {
        return totalKcal;
    }
}