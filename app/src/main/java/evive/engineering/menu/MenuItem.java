package evive.engineering.menu;

public class MenuItem {
    ItemType mItemType;
    String mItemName;

    public MenuItem(String name, ItemType type) {
        mItemName = name;
        mItemType = type;
    }

    public ItemType getItemType() {
        return this.mItemType;
    }

    public void setItemType(ItemType type) {
        this.mItemType = type;
    }

    public String getItemName() {
        return this.mItemName;
    }

    public void setItemName(String name) {
        this.mItemName = name;
    }
}
