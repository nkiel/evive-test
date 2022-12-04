package evive.engineering.menu;

import evive.engineering.Order;

public class LunchMenu extends Menu {
    public LunchMenu(MenuItem... items) {
        super(items);
    }

    public LunchMenu() {
        this(new MenuItem("Sandwich", ItemType.MAIN), new MenuItem("Chips", ItemType.SIDE), new MenuItem("Soda", ItemType.DRINK));
    }

    @Override
    public String printOrder(Order order) {
        return super.printOrder(order, new ItemType[]{ ItemType.MAIN, ItemType.SIDE }, new ItemType[]{ ItemType.SIDE });
    }

    @Override
    public String validOrder(Order order) {
        return super.validOrder(order, new ItemType[]{ ItemType.MAIN, ItemType.SIDE }, new ItemType[]{ ItemType.SIDE });
    }
}
