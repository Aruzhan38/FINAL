package restaurant.observer;

public class CustomerApp implements OrderObserver{
    private final String username;
    public CustomerApp(String username){
        this.username=username;
    }

    @Override
    public void onStatusChanged(String orderId, OrderStatus newStatus) {
        System.out.println("your order is "+ newStatus);
    }
}
