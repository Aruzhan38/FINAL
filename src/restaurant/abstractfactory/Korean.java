package restaurant.abstractfactory;

import restaurant.core.*;
import restaurant.factory.Tteokbokki;

public class Korean implements CuisineFactory {

    @Override
    public String cuisineName() {
        return "Korean Cuisine";
    }
    @Override
    public Meal createMeal() {
        return new Tteokbokki();
    }
    @Override
    public Side createSide()  {
        return new Kimchi();
    }
    @Override
    public Drink createDrink(){
        return new BarleyTea();
    }

    private static class Kimchi implements Side {
        @Override
        public String getName()  {
            return "Kimchi"; }
        @Override
        public double getPrice() {
            return 500;
        }
    }
    private static class BarleyTea implements Drink {
        @Override
        public String getName() {
            return "Barley Tea"; }
        @Override
        public double getPrice() {
            return 400;
        }
    }
}
