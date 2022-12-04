package evive.engineering;

import java.util.HashMap;

public class Order {
    String menu;
    HashMap<Integer, Integer> itemCount;

    public Order(String menu) {
        this.menu = menu;
        itemCount = new HashMap<Integer, Integer>();
    }

    public Order(String menu, int... indexes) {
        this(menu);
        addItems(indexes);
    }

    public String getMenu() {
        return menu;
    }

    public void addItem(int index) {
        int initialCnt = itemCount.containsKey(index) ? itemCount.get(index) : 0;
        itemCount.put(index, initialCnt+1);
    }

    public void addItems(int... indexes) {
        for(int i : indexes) {
            addItem(i);
        }
    }

    public HashMap<Integer, Integer> getOrderItems() {
        return itemCount;
    }

    public int getItemCount(int index) {
        return itemCount.get(index);
    }
}
