package restaurant;

import restaurant.abstractfactory.*;
import restaurant.core.drink.Drink;
import restaurant.core.meal.Meal;
import restaurant.core.side.Side;
import restaurant.facade.OrderFacade;
import restaurant.observer.*;
import restaurant.service.*;
import restaurant.strategy.*;
import java.util.*;

public class Main {
    private static final Scanner in = new Scanner(System.in);

    private static final List<OrderRecord> ORDER_HISTORY = new ArrayList<>();
    private static final AnalyticsService analytics = new AnalyticsService();
    private static final DiscountService discountService = new DiscountService();
    private static final MenuInfoService menuInfoService = new MenuInfoService();

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

            int choice = discountService.askIntInRange("Choose option (1-5): ", 1, 5);

            switch (choice) {
                case 1 -> makeOrder(facade);
                case 2 -> analytics.showOrderHistory(ORDER_HISTORY);
                case 3 -> analytics.showStatistics(ORDER_HISTORY);
                case 4 -> menuInfoService.showDishesInfo();
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

        // Choose cuisine via Abstract Factory
        CuisineFactory factory = chooseCuisine();
        String cuisineName = factory.getClass().getSimpleName();

        // Preview selected set
        showMenuForCuisine(factory);

        // Extras (decorators)
        boolean extraCheese   = false;
        boolean extraSeasons  = false;
        boolean spicySauce    = false;
        boolean freshHerbs    = false;

        boolean wantExtras = discountService.askYesNo("Do you want any extras? (y/n): ");
        if (wantExtras) {
            System.out.println("Choose your options:");
            extraCheese   = discountService.askYesNo("Add Extra Cheese? (y/n): ");
            extraSeasons  = discountService.askYesNo("Add Extra Seasons? (y/n): ");
            spicySauce    = discountService.askYesNo("Add Spicy Sauce? (y/n): ");
            freshHerbs    = discountService.askYesNo("Add Fresh Herbs? (y/n): ");
        } else {
            System.out.println("No extras selected.");
        }

        // discount
        DiscountStrategy strategy = discountService.chooseDiscountStrategy();
        DiscountContext  ctx      = discountService.buildContext(strategy);
        // create order via Facade
        Order order = facade.createOrder( factory, extraCheese, extraSeasons, spicySauce,
                freshHerbs, strategy, ctx, customerName );

        System.out.println("\n--- ORDER STATUS FLOW ---");
        waitEnter("Press ENTER to ACCEPT the order... ");
        facade.updateStatus(order, OrderStatus.ACCEPTED);

        waitEnter("Press ENTER to start COOKING... ");
        facade.updateStatus(order, OrderStatus.COOKING);

        waitEnter("Press ENTER when the order is READY... ");
        facade.updateStatus(order, OrderStatus.READY);

        waitEnter("Press ENTER to mark as DELIVERED... ");
        facade.updateStatus(order, OrderStatus.DELIVERED);

        String discountName = (strategy == null) ? "No discount" : strategy.name();

        ORDER_HISTORY.add( new OrderRecord( order.getId(), customerName, cuisineName, discountName, order.getStatus()));

        System.out.println("\nOrder finished. Returning to main menu...\n");
    }

    private static CuisineFactory chooseCuisine() {
        System.out.println("Choose cuisine set:");
        System.out.println("  1) Kazakh");
        System.out.println("  2) Turkish");
        System.out.println("  3) Korean");

        int choice = discountService.askIntInRange("Your choice (1-3): ", 1, 3);
        return switch (choice) {
            case 1 -> new Kazakh();
            case 2 -> new Turkish();
            case 3 -> new Korean();
            default -> throw new IllegalStateException("Unexpected cuisine: " + choice);
        };
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

    private static void waitEnter(String msg) {
        System.out.print(msg);
        in.nextLine();
    }
}
