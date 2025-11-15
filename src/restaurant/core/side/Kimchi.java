package restaurant.core.side;

public class Kimchi implements Side {
    @Override
    public String getName()  {
        return "Kimchi"; }
    @Override
    public int getPrice() {
        return 500;
    }
}