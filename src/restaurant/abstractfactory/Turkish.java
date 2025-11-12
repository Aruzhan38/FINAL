package restaurant.abstractfactory;

import restaurant.core.*;
import restaurant.factory.IskenderKebab;

public class Turkish implements CuisineFactory {

    @Override
    public String cuisineName() {
        return "Turkish Cuisine";
    }
    @Override
    public Meal createMeal() {
        return new IskenderKebab();
    }
    @Override
    public Side createSide()  {
        return new Pilav();
    }
    @Override
    public Drink createDrink(){
        return new Ayran();
    }

    private static class Pilav implements Side {
        @Override
        public String getName()  {
            return "Pilav"; }
        @Override
        public double getPrice() {
            return 700;
        }
    }
    private static class Ayran implements Drink {
        @Override
        public String getName() {
            return "Ayran"; }
        @Override
        public double getPrice() {
            return 650;
        }
    }
}
