package restaurant.strategy;
import java.util.Random;

public class DiceRoll implements DiscountStrategy {
    public DiscountContext ctx;
    private final Random random = new Random();

    @Override
    public void collect(DiscountContext ctx) {
        this.ctx = ctx;
        if (this.ctx.diceRoll == null) {
            this.ctx.diceRoll = random.nextInt(6) + 1;
            System.out.println("dice roll number is " + this.ctx.diceRoll);
        }
    }

    @Override
    public boolean validate() {

        return ctx != null && ctx.diceRoll != null && ctx.diceRoll >= 1 && ctx.diceRoll <= 6;
    }

    @Override
    public int apply(int basePrice) {
        int r = ctx.diceRoll;
        int percent = switch (r) {
            case 1 -> 0;
            case 2 -> 5;
            case 3 -> 10;
            case 4 -> 20;
            case 5 -> 40;
            case 6 -> 50;
            default -> 0;
        };
        double k = 1.0 - percent / 100.0;
        return (int) Math.round(basePrice * k);
    }

    @Override
    public String name() {
        return "Dice roll discount!: " + ctx.diceRoll;
    }
}
