package restaurant.abstractfactory;

import restaurant.core.*;
import restaurant.factory.Manty;

public final class KazakhFactory implements CuisineFactory {

    @Override
    public String cuisineName() {
        return "Kazakh Cuisine";
    }
    @Override
    public Meal meal() {
        return new Manty();
    }
    @Override
    public Side side()  {
        return new Baursaki();
    }
    @Override
    public Drink drink(){
        return new Ayran();
    }

    public static final class Baursaki implements Side {
        @Override
        public String name()  {
            return "Baursaki"; }
        @Override
        public int price() {
            return 600;
        }
    }
    public static final class Ayran implements Drink {
        @Override
        public String name() {
            return "Ayran"; }
        @Override
        public int price() {
            return 500;
        }
    }
}
