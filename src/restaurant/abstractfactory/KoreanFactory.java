package restaurant.abstractfactory;

import restaurant.core.*;
import restaurant.factory.Tteokbokki;

public final class KoreanFactory implements CuisineFactory {

    @Override
    public String cuisineName() {
        return "Korean Cuisine";
    }
    @Override
    public Meal meal() {
        return new Tteokbokki();
    }
    @Override
    public Side side() {
        return new KimbapHalf();
    }
    @Override
    public Drink drink() {
        return new KoreanLemonade();
    }

    public static final class KimbapHalf implements Side {
        @Override
        public String name() {
            return "Kimbab (½ roll)";
        }
        @Override
        public int price() {
            return 900;
        }
    }
    public static final class KoreanLemonade implements Drink {
        @Override
        public String name() {
            return "Korean Lemonade";
        }
        @Override
        public int price() {
            return 800;
        }
    }
}
