package restaurant.visitor;

import restaurant.core.meal.*;
import restaurant.core.drink.*;
import restaurant.core.side.*;
import restaurant.core.*;
import restaurant.decorator.*;
import java.util.HashMap;
import java.util.Map;

public class NutritionVisitor implements MealVisitor {

    private final Map<Class<?>, Integer> kcalByType = new HashMap<>();
    private int totalKcal = 0;

    public NutritionVisitor() {

        kcalByType.put(Manty.class, 420);
        kcalByType.put(IskenderKebab.class, 650);
        kcalByType.put(Tteokbokki.class, 380);

        kcalByType.put(Baursak.class, 250);
        kcalByType.put(Pilav.class, 270);
        kcalByType.put(Kimchi.class, 40);

        kcalByType.put(Kymyz.class, 120);
        kcalByType.put(Ayran.class, 90);
        kcalByType.put(BarleyTea.class, 5);

        kcalByType.put(SpicySauce.class, 20);
        kcalByType.put(ExtraCheese.class, 110);
        kcalByType.put(FreshHerbs.class, 5);
        kcalByType.put(ExtraSeasons.class, 120);
    }

    private void add(Object obj) {
        Integer kcal = kcalByType.get(obj.getClass());
        if (kcal != null) {
            totalKcal += kcal;
        }
    }
    @Override
    public void visit(Meal meal)  { add(meal); }

    @Override
    public void visit(Side side)  { add(side); }

    @Override
    public void visit(Drink drink){ add(drink); }

    public int getTotalKcal() {
        return totalKcal;
    }
}
