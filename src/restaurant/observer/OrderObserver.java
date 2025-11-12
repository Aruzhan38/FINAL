package restaurant.observer;

public interface OrderObserver {
    void onStatusChanged(String orderId, OrderStatus newStatus);
}
