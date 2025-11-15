package restaurant.strategy;

import restaurant.core.Meal;
import java.time.LocalDate;
import java.time.LocalTime;

public class DiscountContext {
    public Meal meal;
    public LocalDate today;
    public LocalTime now;
    public boolean wearingPink;
    public Weather weather;
    public boolean spicyChallenge;
    public Integer diceRoll;
}
