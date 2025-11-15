package restaurant.service;

import restaurant.abstractfactory.*;
import restaurant.core.drink.Drink;
import restaurant.core.meal.Meal;
import restaurant.core.side.Side;
import restaurant.visitor.NutritionVisitor;
import java.util.Scanner;

public class MenuInfoService {

    private final Scanner in = new Scanner(System.in);

    public void showDishesInfo() {
        System.out.println("\n=== DISHES INFO (PRICE + KCAL) ===");

        System.out.println("\n[Meals]");
        printMeal(new Kazakh().createMeal());
        printMeal(new Turkish().createMeal());
        printMeal(new Korean().createMeal());

        System.out.println("\n[Sides]");
        printSide(new Kazakh().createSide());
        printSide(new Turkish().createSide());
        printSide(new Korean().createSide());

        System.out.println("\n[Drinks]");
        printDrink(new Kazakh().createDrink());
        printDrink(new Turkish().createDrink());
        printDrink(new Korean().createDrink());

        System.out.println("----------------------------------");
        System.out.println("Press ENTER to return to the menu...");
        in.nextLine();
    }

    private void printMeal(Meal meal) {
        System.out.printf("- %s | price: %d%n",
                meal.getName(), meal.getPrice());
    }

    private void printSide(Side side) {
        NutritionVisitor v = new NutritionVisitor();
        side.accept(v);
        System.out.printf("- %s | price: %d | kcal: %d%n",
                side.getName(), side.getPrice(), v.getTotalKcal());
    }

    private void printDrink(Drink drink) {
        NutritionVisitor v = new NutritionVisitor();
        drink.accept(v);
        System.out.printf("- %s | price: %d | kcal: %d%n",
                drink.getName(), drink.getPrice(), v.getTotalKcal());
    }
}
