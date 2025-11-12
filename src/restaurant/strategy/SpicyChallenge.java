package restaurant.strategy;

public class SpicyChallenge implements DiscountStrategy{
    private DiscountContext ctx;

    @Override
    public void collect(DiscountContext ctx) {
        this.ctx=ctx;
        
    }

    @Override
    public boolean validate() {
        return ctx!=null && ctx.spicyChallenge;
    }

    @Override
    public int apply(int basePrice) {
        return (int)Math.round(basePrice*0.5);
    }

    @Override
    public String name() {
        return "you got discount for spicy challenge";
    }
}
