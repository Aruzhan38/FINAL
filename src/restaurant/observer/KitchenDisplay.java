package restaurant.observer;

public class KitchenDisplay implements OrderObserver{
    private final String kitchen;
    public KitchenDisplay(String kitchen){
        this.kitchen=kitchen;
    }

    @Override
    public void onStatusChanged(String orderId, OrderStatus newStatus) {
        System.out.println("kitchen: "+kitchen+" order: "+orderId+newStatus);
    }
}
