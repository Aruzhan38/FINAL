package restaurant.decorator;

import restaurant.core.Drink;
import restaurant.core.Meal;
import restaurant.core.MealVisitor;
import restaurant.core.Side;

import java.util.Collections;
import java.util.Set;

public abstract class Combo implements Meal {
    private final Meal main;
    private final Drink drink;
    private final Side side;
    private final int discount;
    private final String label;

    public Combo(Meal main,Drink drink,Side side,int discount, String label){
        this.main=main;
        this.drink=drink;
        this.side=side;
        this.discount=discount;
        this.label=label;
    }

    @Override
    public String getName(){
        return label + ": " + main.getName() + " + " + drink.getName() + " + " + side.getName();
    }

    @Override
    public double getPrice() {
        double sum= main.getPrice()+ drink.getPrice()+ side.getPrice();
        return Math.max(0, sum - discount);
    }

}
