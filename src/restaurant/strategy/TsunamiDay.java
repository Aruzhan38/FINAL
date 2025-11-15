package restaurant.strategy;

public class TsunamiDay implements DiscountStrategy{
    private DiscountContext ctx;

    @Override
    public void collect(DiscountContext ctx) {
        this.ctx=ctx;
    }

    @Override
    public boolean validate() {
        return ctx!=null && ctx.weather == Weather.TSUNAMI;
    }

    @Override
    public int apply(int basePrice) {
        return (int)Math.round(basePrice*0.3);
    }

    @Override
    public String name() {
        return "yay you got discount , for tsunami day!";
    }
}
