package restaurant.strategy;

import restaurant.core.Meal;
import java.time.LocalDate;
import java.time.LocalTime;

public class DiscountContext {
    public Meal meal;
    public LocalDate today=LocalDate.now();
    public LocalTime now=LocalTime.now();
    public boolean wearingPink;
    public Weather weather=Weather.TSUNAMI;
    public boolean spicyChallenge;
    public Integer diceRoll;
}
