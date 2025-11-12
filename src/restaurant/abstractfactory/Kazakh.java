package restaurant.abstractfactory;

import restaurant.core.*;
import restaurant.factory.Manty;

public class Kazakh implements CuisineFactory {

    @Override
    public String cuisineName() {
        return "Kazakh Cuisine";
    }
    @Override
    public Meal createMeal() {
        return new Manty();
    }
    @Override
    public Side createSide()  {
        return new Baursak();
    }
    @Override
    public Drink createDrink(){
        return new Kymyz();
    }

    private static class Baursak implements Side {
        @Override
        public String getName()  {
            return "Baursak"; }
        @Override
        public double getPrice() {
            return 600;
        }
    }
    private static class Kymyz implements Drink {
        @Override
        public String getName() {
            return "Kymyz"; }
        @Override
        public double getPrice() {
            return 900;
        }
    }
}
