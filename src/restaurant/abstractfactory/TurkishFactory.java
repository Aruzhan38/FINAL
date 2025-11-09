package restaurant.abstractfactory;

import restaurant.core.*;
import restaurant.factory.IskenderKebab;

public final class TurkishFactory implements CuisineFactory {

    @Override
    public String cuisineName() {
        return "Turkish Cuisine";
    }

    @Override
    public Meal meal() {
        return new IskenderKebab();
    }
    @Override
    public Side side() {
        return new Pide();
    }
    @Override
    public Drink drink(){
        return new Ayran();
    }

    public static final class Pide implements Side {
        @Override
        public String name()  {
            return "Pide";
        }
        @Override
        public int price() {
            return 700;
        }
    }
    public static final class Ayran implements Drink {
        @Override
        public String name()  {
            return "Ayran"; }
        @Override
        public int price() {
            return 500; }
    }
}
