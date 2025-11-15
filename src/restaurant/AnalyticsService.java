package restaurant;

import restaurant.observer.*;
import java.util.*;

public class AnalyticsService {
    private final Scanner in = new Scanner(System.in);

    public void showOrderHistory(List<OrderRecord> history) {
        System.out.println("\n=== ORDER HISTORY ===");

        if (history.isEmpty()) {
            System.out.println("No orders yet.");
        } else {
            for (OrderRecord r : history) {
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
        System.out.println("Press ENTER to return to menu...");
        in.nextLine();
    }

    public void showStatistics(List<OrderRecord> history) {
        System.out.println("\n=== STATISTICS ===");

        int total = history.size();
        int delivered = 0;

        int kazakh = 0, turkish = 0, korean = 0;

        for (OrderRecord r : history) {
            switch (r.cuisine.toLowerCase()) {
                case "kazakh"  -> kazakh++;
                case "turkish" -> turkish++;
                case "korean"  -> korean++;
            }

            if (r.finalStatus == OrderStatus.DELIVERED)
                delivered++;
        }

        System.out.println("Total orders   : " + total);
        System.out.println("Delivered      : " + delivered);
        System.out.println("By cuisine:");
        System.out.println("  Kazakh       : " + kazakh);
        System.out.println("  Turkish      : " + turkish);
        System.out.println("  Korean       : " + korean);

        System.out.println("----------------------------------");
        System.out.println("Press ENTER to return to menu...");
        in.nextLine();
    }
}

