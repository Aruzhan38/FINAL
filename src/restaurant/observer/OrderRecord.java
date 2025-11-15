package restaurant.observer;

public class OrderRecord {
    public String orderId;
    public String customerName;
    public String cuisine;
    public String discountName;
    public OrderStatus finalStatus;

    public OrderRecord(String orderId,
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