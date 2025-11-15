package restaurant.visitor;

import restaurant.core.*;
import restaurant.core.MealVisitor;
import java.util.HashMap;
import java.util.Map;

public class NutritionVisitor implements MealVisitor {
    private final Map<String, Integer> kcalByName = new HashMap<>();
    private int totalKcal = 0;

    public NutritionVisitor() {

        kcalByName.put("manty", 420);
        kcalByName.put("iskender kebab", 650);
        kcalByName.put("tteokbokki", 380);

        kcalByName.put("baursak", 250);
        kcalByName.put("pilav", 270);
        kcalByName.put("kimchi", 40);

        kcalByName.put("kymyz", 120);
        kcalByName.put("ayran", 90);
        kcalByName.put("barley tea", 5);

        kcalByName.put("spicy sauce", 20);
        kcalByName.put("extra cheese", 110);
        kcalByName.put("extra meat", 180);
        kcalByName.put("fresh herbs", 5);
    }

    public NutritionVisitor with(String name, int kcal) {
        kcalByName.put(name.toLowerCase(), kcal);
        return this;
    }

    @Override public void visit(Meal meal)  { add(meal.getName()); }
    @Override public void visit(Side side)  { add(side.getName()); }
    @Override public void visit(Drink drink){ add(drink.getName()); }

    private void add(String name) {
        String lower = name.toLowerCase();
        for (Map.Entry<String, Integer> e : kcalByName.entrySet()) {
            if (lower.contains(e.getKey())) {
                totalKcal += e.getValue();
            }
        }
    }


    public int getTotalKcal() {
        return totalKcal;
    }
}
