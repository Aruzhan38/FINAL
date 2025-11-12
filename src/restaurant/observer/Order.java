package restaurant.observer;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final String id;
    private OrderStatus status = OrderStatus.CREATED;
    private final List<OrderObserver> observers=new ArrayList<>();
    public Order(String id){
        this.id=id;
    }
    public String getId(){
        return id;
    }
    public OrderStatus getStatus(){
        return status;
    }

    public void addObserver(OrderObserver o){
        observers.add(o);
    }
    public void removeObserver(OrderObserver o){
        observers.remove(o);
    }
    public void setStatus(OrderStatus newStatus){
        if (this.status==newStatus) return;
        this.status=newStatus;
        for (OrderObserver o : observers){
            o.onStatusChanged(id,newStatus);
        }
    }
}
