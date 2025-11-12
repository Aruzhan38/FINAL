package restaurant.strategy;

public interface DiscountStrategy {
    void collect(DiscountContext ctx);
    boolean validate();
    int apply(int basePrice);
    String name();
}
