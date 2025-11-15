package restaurant.strategy;

import restaurant.core.meal.Meal;
import java.time.*;

public class DiscountContext {
    public Meal meal;
    public LocalDate today;
    public LocalTime now;
    public boolean wearingPink;
    public Weather weather;
    public boolean spicyChallenge;
    public Integer diceRoll;
}
