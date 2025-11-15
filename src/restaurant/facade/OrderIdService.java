package restaurant.facade;

public class OrderIdService {
    // unique order ID
    private int counter = 1;
    String nextOrderId() {
        return "ORDER-" + (counter++) + " ";
    }
}
