package evive.engineering.menu;

import java.util.ArrayList;
import java.util.Arrays;

import evive.engineering.Order;

public class Menu {
    ArrayList<MenuItem> menuItems;

    public Menu() {
        menuItems = new ArrayList<>();
    }

    public Menu(MenuItem... items) {
        this();
        menuItems.addAll(Arrays.asList(items));
    }

    /*
     * Menu items stored with start index 0 but accessed with start index 1, perform
     * conversions here
     */
    public String getMenuItemName(int x) {
        return getMenuItem(x).getItemName();
    }

    public ItemType getMenuItemType(int x) {
        return getMenuItem(x).getItemType();
    }

    public MenuItem getMenuItem(int x) {
        return menuItems.get(x - 1);
    }

    public String validOrder(Order order) {
        return validOrder(order, new ItemType[] { ItemType.MAIN, ItemType.SIDE }, null);
    }

    /**
     * Returns null String on valid order otherwise returns error message
     * 
     * @param order - Order to verify
     * @return null if valid otherwise error message
     */
    public String validOrder(Order order, ItemType[] required, ItemType[] duplicates) {
        StringBuilder outErr = new StringBuilder();
        int[] contains = new int[ItemType.values().length];
        boolean[] dupLookup = new boolean[ItemType.values().length];
        for (int i = 0; i < contains.length; i++) {
            contains[i] = 0;
            dupLookup[i] = false;
        }
        if (duplicates != null) {
            for (ItemType type : duplicates) {
                dupLookup[type.ordinal()] = true;
            }
        }
        for (Integer key : order.getOrderItems().keySet()) {
            if (key < 1 || key > this.menuItems.size()) {
                if (outErr.length() > 0) {
                    outErr.append(", ");
                }
                outErr.append("id " + key + " does not have a corrosponding item");
            } else {
                contains[getMenuItemType(key).ordinal()] += order.getItemCount(key);
                if (order.getItemCount(key) > 1) {
                    if (!dupLookup[getMenuItemType(key).ordinal()]) {
                        if (outErr.length() > 0) {
                            outErr.append(", ");
                        }
                        outErr.append(getMenuItemName(key));
                        outErr.append(" cannot be ordered more than once");
                    }
                }
            }
        }
        if (required != null) {
            for (ItemType type : required) {
                if (contains[type.ordinal()] < 1) {
                    if (outErr.length() > 0) {
                        outErr.append(", ");
                    }
                    outErr.append(type);
                    outErr.append(" is missing");
                }
            }
        }

        return outErr.length() > 0 ? outErr.toString() : null;
    }

    public String printOrder(Order order, ItemType[] required, ItemType[] duplicates) {
        String validity = validOrder(order, required, duplicates);
        if (validity != null) {
            return "Unable to process: " + validity;
        }
        StringBuilder[] builders = new StringBuilder[ItemType.values().length];
        for (int i = 0; i < builders.length; i++) {
            builders[i] = new StringBuilder();
        }
        for (Integer key : order.getOrderItems().keySet()) {
            StringBuilder menuBuilder = builders[getMenuItemType(key).ordinal()];
            if (menuBuilder.length() > 0) {
                menuBuilder.append(", ");
            }
            menuBuilder.append(getMenuItemName(key));
            int count = order.getItemCount(key);
            if (count > 1) {
                menuBuilder.append(String.format("(%d)", count));
            }
        }
        for (int i = 1; i < builders.length; i++) {
            if (builders[i].length() > 0) {
                if(builders[0].length() > 0){
                    builders[0].append(", ");
                }
                builders[0].append(builders[i].toString());
                if ((ItemType.DRINK.ordinal() == i) && order.getMenu().compareTo("Dinner") == 0) {
                    if(builders[0].length() > 0){
                        builders[0].append(", ");
                    }
                    builders[0].append("Water");
                }
            } else if (i == ItemType.DRINK.ordinal()) {
                if(builders[0].length() > 0){
                    builders[0].append(", ");
                }
                builders[0].append("Water");
            }
        }
        return builders[0].toString();
    }

    public String printOrder(Order order) {
        return printOrder(order, new ItemType[] { ItemType.MAIN, ItemType.SIDE }, null);
        
    }
}
