package evive.engineering.menu;

import evive.engineering.Order;

public class BreakfastMenu extends Menu {
    public BreakfastMenu(MenuItem... items) {
        super(items);
    }

    public BreakfastMenu() {
        this(new MenuItem("Eggs", ItemType.MAIN), new MenuItem("Toast", ItemType.SIDE), new MenuItem("Coffee", ItemType.DRINK));
    }

    @Override
    public String printOrder(Order order) {
        return super.printOrder(order, new ItemType[]{ ItemType.MAIN, ItemType.SIDE }, new ItemType[]{ ItemType.DRINK });
    }

    @Override
    public String validOrder(Order order) {
        return super.validOrder(order, new ItemType[]{ ItemType.MAIN, ItemType.SIDE }, new ItemType[]{ ItemType.DRINK });
    }
}
