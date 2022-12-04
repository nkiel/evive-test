package evive.engineering.menu;

import evive.engineering.Order;

public class DinnerMenu extends Menu {
    public DinnerMenu(MenuItem... items) {
        super(items);
    }

    public DinnerMenu() {
        this(new MenuItem("Steak", ItemType.MAIN), new MenuItem("Potatoes", ItemType.SIDE), new MenuItem("Wine", ItemType.DRINK), new MenuItem("Cake", ItemType.DESSERT));
    }

    @Override
    public String printOrder(Order order) {
        return super.printOrder(order, new ItemType[]{ ItemType.MAIN, ItemType.SIDE, ItemType.DESSERT }, null);
    }

    @Override
    public String validOrder(Order order) {
        return super.validOrder(order, new ItemType[]{ ItemType.MAIN, ItemType.SIDE, ItemType.DESSERT }, null);
    }
}
