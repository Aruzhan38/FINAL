package restaurant.strategy;

import java.time.DayOfWeek;

public class PinkFriday implements DiscountStrategy{
    private DiscountContext ctx;

    @Override
    public void collect(DiscountContext ctx) {
        this.ctx=ctx;
    }

    @Override
    public boolean validate() {
        return ctx != null && ctx.wearingPink && ctx.today.getDayOfWeek()== DayOfWeek.FRIDAY;
    }

    @Override
    public int apply(int basePrice) {
        return (int)Math.round(basePrice*0.88);
    }

    @Override
    public String name() {
        return "discount wearing pink in friday!";
    }
}
