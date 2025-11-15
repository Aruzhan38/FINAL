package restaurant;

import restaurant.abstractfactory.*;
import restaurant.core.*;
import restaurant.facade.OrderFacade;
import restaurant.observer.Order;
import restaurant.observer.OrderStatus;
import restaurant.strategy.*;
import restaurant.visitor.NutritionVisitor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Main {

    private static final Scanner in = new Scanner(System.in);

    private static final List<OrderRecord> ORDER_HISTORY = new ArrayList<>();

    private static class OrderRecord {
        final String orderId;
        final String customerName;
        final String cuisine;
        final String discountName;
        final OrderStatus finalStatus;

        OrderRecord(String orderId,
                    String customerName,
                    String cuisine,
                    String discountName,
                    OrderStatus finalStatus) {
            this.orderId = orderId;
            this.customerName = customerName;
            this.cuisine = cuisine;
            this.discountName = discountName;
            this.finalStatus = finalStatus;
        }
    }

    public static void main(String[] args) {

        OrderFacade facade = new OrderFacade();

        System.out.println("=== RESTAURANT ORDERING SYSTEM ===");

        while (true) {
            System.out.println("\nMAIN MENU:");
            System.out.println("  1) Make a new order");
            System.out.println("  2) View order history");
            System.out.println("  3) View statistics");
            System.out.println("  4) View dishes info");
            System.out.println("  5) Exit\n");

            int choice = askIntInRange("Choose option (1-5): ", 1, 5);

            switch (choice) {
                case 1 -> makeOrder(facade);
                case 2 -> showOrderHistory();
                case 3 -> showStatistics();
                case 4 -> showDishesInfo();
                case 5 -> {
                    System.out.println("\nThank you for using our system. Goodbye! 👋");
                    return;
                }
            }
        }
    }

    private static void makeOrder(OrderFacade facade) {

        System.out.print("\nEnter your name: ");
        String customerName = in.nextLine().trim();
        if (customerName.isEmpty()) customerName = "Guest";

        CuisineFactory factory = chooseCuisine();
        String cuisineName = factory.getClass().getSimpleName();

        showMenuForCuisine(factory);
        boolean extraCheese=false;
        boolean extraSeasons=false;
        boolean spicySauce=false;
        boolean freshHerbs=false;
        boolean wantExtras=askYesNo("do you want any extras? (y/n) ");

        if(wantExtras){
            System.out.println("choose your options");
            extraCheese   = askYesNo("Add Extra Cheese? (y/n): ");
            extraSeasons  = askYesNo("Add Extra Seasons? (y/n): ");
            spicySauce    = askYesNo("Add Spicy Sauce? (y/n): ");
            freshHerbs    = askYesNo("Add Fresh Herbs? (y/n): ");

        }
        else{
            System.out.println("no extra");
        }




        DiscountStrategy strategy = chooseDiscountStrategy();
        DiscountContext ctx = buildDiscountContext(strategy);

        Order order = facade.createOrder(
                factory,
                extraCheese,
                extraSeasons,
                spicySauce,
                freshHerbs,
                strategy,
                ctx,
                customerName
        );

        System.out.println("\n--- ORDER STATUS FLOW ---");
        waitEnter("Press ENTER to ACCEPT the order...");
        facade.updateStatus(order, OrderStatus.ACCEPTED);

        waitEnter("Press ENTER to start COOKING...");
        facade.updateStatus(order, OrderStatus.COOKING);

        waitEnter("Press ENTER when the order is READY...");
        facade.updateStatus(order, OrderStatus.READY);

        waitEnter("Press ENTER to mark as DELIVERED...");
        facade.updateStatus(order, OrderStatus.DELIVERED);

        String discountName = (strategy == null) ? "No discount" : strategy.name();
        ORDER_HISTORY.add(
                new OrderRecord(
                        order.getId(),
                        customerName,
                        cuisineName,
                        discountName,
                        order.getStatus()
                )
        );

        System.out.println("\nOrder finished. Returning to main menu...\n");
    }

    private static void showOrderHistory() {
        System.out.println("\n=== ORDER HISTORY ===");
        if (ORDER_HISTORY.isEmpty()) {
            System.out.println("No orders yet.");
        } else {
            for (OrderRecord r : ORDER_HISTORY) {
                System.out.println(
                        r.orderId + " | " +
                                "customer: " + r.customerName + " | " +
                                "cuisine: " + r.cuisine + " | " +
                                "discount: " + r.discountName + " | " +
                                "status: " + r.finalStatus
                );
            }
        }
        System.out.println("----------------------------------");
        System.out.println("Press ENTER to return to main menu...");
        in.nextLine();
    }

    private static void showStatistics() {
        System.out.println("\n=== STATISTICS ===");

        int total = ORDER_HISTORY.size();
        int kazakh = 0, turkish = 0, korean = 0;
        int delivered = 0;

        for (OrderRecord r : ORDER_HISTORY) {
            switch (r.cuisine.toLowerCase()) {
                case "kazakh"  -> kazakh++;
                case "turkish" -> turkish++;
                case "korean"  -> korean++;
            }
            if (r.finalStatus == OrderStatus.DELIVERED) {
                delivered++;
            }
        }

        System.out.println("Total orders   : " + total);
        System.out.println("Delivered      : " + delivered);
        System.out.println("By cuisine:");
        System.out.println("  Kazakh       : " + kazakh);
        System.out.println("  Turkish      : " + turkish);
        System.out.println("  Korean       : " + korean);

        System.out.println("----------------------------------");
        System.out.println("Press ENTER to return to main menu...");
        in.nextLine();
    }

    private static void showDishesInfo() {
        System.out.println("\n=== DISHES INFO (PRICE + KCAL) ===");

        System.out.println("\n[Meals]");
        printMealInfo(new Kazakh().createMeal());
        printMealInfo(new Turkish().createMeal());
        printMealInfo(new Korean().createMeal());

        System.out.println("\n[Sides]");
        printSideInfo(new Kazakh().createSide());
        printSideInfo(new Turkish().createSide());
        printSideInfo(new Korean().createSide());

        System.out.println("\n[Drinks]");
        printDrinkInfo(new Kazakh().createDrink());
        printDrinkInfo(new Turkish().createDrink());
        printDrinkInfo(new Korean().createDrink());

        System.out.println("----------------------------------");
        System.out.println("Press ENTER to return to main menu...");
        in.nextLine();
    }

    private static void printMealInfo(Meal meal) {
        NutritionVisitor v = new NutritionVisitor();
        meal.accept(v);
        System.out.printf("- %s | price: %.2f%n",
                meal.getName(), meal.getPrice());
    }

    private static void printSideInfo(Side side) {
        NutritionVisitor v = new NutritionVisitor();
        side.accept(v);
        System.out.printf("- %s | price: %.2f | kcal: %d%n",
                side.getName(), side.getPrice(), v.getTotalKcal());
    }

    private static void printDrinkInfo(Drink drink) {
        NutritionVisitor v = new NutritionVisitor();
        drink.accept(v);
        System.out.printf("- %s | price: %.2f | kcal: %d%n",
                drink.getName(), drink.getPrice(), v.getTotalKcal());
    }

    private static CuisineFactory chooseCuisine() {
        System.out.println("Choose cuisine set:");
        System.out.println("  1) Kazakh");
        System.out.println("  2) Turkish");
        System.out.println("  3) Korean");

        int choice = askIntInRange("Your choice (1-3): ", 1, 3);
        switch (choice) {
            case 1: return new Kazakh();
            case 2: return new Turkish();
            case 3: return new Korean();
            default: throw new IllegalStateException("Unexpected cuisine: " + choice);
        }
    }

    private static DiscountStrategy chooseDiscountStrategy() {
        System.out.println("\nChoose discount type:");
        System.out.println("  0) No discount");
        System.out.println("  1) Pink Friday");
        System.out.println("  2) Dice roll");
        System.out.println("  3) Tsunami Day");
        System.out.println("  4) Spicy Challenge");

        int choice = askIntInRange("Your choice (0-4): ", 0, 4);
        switch (choice) {
            case 0: return null;
            case 1: return new PinkFriday();
            case 2: return new DiceRoll();
            case 3: return new TsunamiDay();
            case 4: return new SpicyChallenge();
            default: throw new IllegalStateException("Unexpected strategy: " + choice);
        }
    }

    private static final Random random=new Random();

    private static DiscountContext buildDiscountContext(DiscountStrategy strategy) {
        DiscountContext ctx = new DiscountContext();
        DayOfWeek[] days= DayOfWeek.values();
        DayOfWeek randomDay=days[random.nextInt(days.length)];
        LocalDate today=LocalDate.now();
        while (today.getDayOfWeek()!=randomDay){
            today=today.plusDays(1);
        }
        ctx.today=today;
        System.out.println("Today is "+randomDay);


        if(strategy instanceof PinkFriday){
            if(randomDay==DayOfWeek.FRIDAY){
                ctx.wearingPink=askYesNo("are you wearing pink today?");
            }
            else{
                System.out.println("today is not friday, no discount bro");
            }
            return ctx;
        }
        if (strategy instanceof TsunamiDay){
            Weather[] weathers=Weather.values();
            ctx.weather=weathers[random.nextInt(weathers.length)];
            System.out.println("today weather is "+ctx.weather);
        }

        if (strategy instanceof DiceRoll){
            System.out.println("dice roll will randomly picked: ");
        }

        if (strategy instanceof SpicyChallenge){
            ctx.spicyChallenge = askYesNo("Did you finish the spicy challenge? (y/n): ");
        }
        return ctx;
    }

    private static boolean askYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = in.nextLine().trim().toLowerCase(Locale.ROOT);
            if (s.equals("y") || s.equals("yes")) return true;
            if (s.equals("n") || s.equals("no"))  return false;
            System.out.println("Please answer 'y' or 'n'.");
        }
    }

    private static int askIntInRange(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = in.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value >= min && value <= max) {
                    return value;
                }
            } catch (Exception ignored) {}
            System.out.printf("Please enter a number between %d and %d.%n", min, max);
        }
    }

    private static void waitEnter(String msg) {
        System.out.print(msg);
        in.nextLine();
    }

    private static void showMenuForCuisine(CuisineFactory factory) {
        Meal  previewMeal  = factory.createMeal();
        Side  previewSide  = factory.createSide();
        Drink previewDrink = factory.createDrink();

        System.out.println("-- SELECTED SET MENU ---");
        System.out.println("Main dish : " + previewMeal.getName());
        System.out.println("Side dish : " + previewSide.getName());
        System.out.println("Drink     : " + previewDrink.getName());
        System.out.println("(You can add extras and discounts)\n");
    }
}
