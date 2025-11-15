package restaurant.service;

import restaurant.strategy.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class DiscountService {

    private final Scanner in = new Scanner(System.in);
    private final Random random = new Random();

    public DiscountStrategy chooseDiscountStrategy() {
        System.out.println("\nChoose discount type:");
        System.out.println("  0) No discount");
        System.out.println("  1) Pink Friday");
        System.out.println("  2) Dice Roll");
        System.out.println("  3) Tsunami Day");
        System.out.println("  4) Spicy Challenge");

        int choice = askIntInRange("Your choice (0-4): ", 0, 4);

        return switch (choice) {
            case 0 -> null;
            case 1 -> new PinkFriday();
            case 2 -> new DiceRoll();
            case 3 -> new TsunamiDay();
            case 4 -> new SpicyChallenge();
            default -> throw new IllegalStateException("Unexpected value: " + choice);
        };
    }

    public DiscountContext buildContext(DiscountStrategy strategy) {
        DiscountContext ctx = new DiscountContext();

        // random day generator
        DayOfWeek[] days = DayOfWeek.values();
        DayOfWeek randomDay = days[random.nextInt(days.length)];

        LocalDate today = LocalDate.now();
        while (today.getDayOfWeek() != randomDay) {
            today = today.plusDays(1);
        }
        ctx.today = today;

        System.out.println("Today is: " + randomDay);
        // Pink Friday logic
        if (strategy instanceof PinkFriday) {
            if (randomDay == DayOfWeek.FRIDAY) {
                ctx.wearingPink = askYesNo("Are you wearing pink today? (y/n): ");
            } else {
                System.out.println("Today is not Friday — no discount available.");
            }
        }
        // Tsunami Day logic
        if (strategy instanceof TsunamiDay) {
            Weather[] weathers = Weather.values();
            ctx.weather = weathers[random.nextInt(weathers.length)];
            System.out.println("Weather today is: " + ctx.weather);
        }
        // DiceRoll logic
        if (strategy instanceof DiceRoll) {
            System.out.println("Dice roll will be applied automatically.");
        }
        // SpicyChallenge logic
        if (strategy instanceof SpicyChallenge) {
            ctx.spicyChallenge = askYesNo("Did you complete the spicy challenge? (y/n): ");
        }
        return ctx;
    }

    public boolean askYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = in.nextLine().trim().toLowerCase(Locale.ROOT);
            if (s.equals("y") || s.equals("yes")) return true;
            if (s.equals("n") || s.equals("no"))  return false;
            System.out.println("Please answer 'y' or 'n'.");
        }
    }

    public int askIntInRange(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int val = Integer.parseInt(in.nextLine().trim());
                if (val >= min && val <= max) return val;
            } catch (Exception ignored) {}
            System.out.printf("Please enter a number between %d and %d%n", min, max);
        }
    }
}
