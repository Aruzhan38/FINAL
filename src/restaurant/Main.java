package restaurant;

import restaurant.abstractfactory.*;
import restaurant.builder.*;
import restaurant.core.*;
import restaurant.visitor.NutritionVisitor;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Kazakh Factory ===");
        var kzFactory = new KazakhFactory();
        Combo kzCombo = ComboBuilder.fromCuisine(kzFactory)
                .note("Sauce separately")
                .build();
        System.out.println(kzCombo.info());

        System.out.println("\n=== Turkish Factory ===");
        var trFactory = new TurkishFactory();
        Combo trCombo = ComboBuilder.fromCuisine(trFactory)
                .note("No spicy sauce")
                .build();
        System.out.println(trCombo.info());

        System.out.println("\n=== Korean Factory ===");
        var krFactory = new KoreanFactory();
        Combo krCombo = ComboBuilder.fromCuisine(krFactory)
                .note("Add more sauce")
                .build();
        System.out.println(krCombo.info());

        System.out.println("\n=== Custom Mixed Combo ===");
        Combo custom = new ComboBuilder()
                .name("Asian Fusion Mix")
                .meal(kzFactory.meal())
                .side(trFactory.side())
                .drink(krFactory.drink())
                .note("Test mixed combo")
                .build();
        System.out.println(custom.info());

        var kz = new KazakhFactory();
        var tr = new TurkishFactory();
        var kr = new KoreanFactory();

        var kzMeal = kz.meal();
        var trMeal = tr.meal();
        var krMeal = kr.meal();

        System.out.println("\n=== Nutrition by cuisine ===");

        var v1 = new NutritionVisitor();
        kzMeal.accept(v1);
        System.out.println("Kazakh meal: " + v1.getCalories() + " kcal, allergens " + v1.getAllergens());

        var v2 = new NutritionVisitor();
        trMeal.accept(v2);
        System.out.println("Turkish meal: " + v2.getCalories() + " kcal, allergens " + v2.getAllergens());

        var v3 = new NutritionVisitor();
        krMeal.accept(v3);
        System.out.println("Korean meal: " + v3.getCalories() + " kcal, allergens " + v3.getAllergens());

    }
}
