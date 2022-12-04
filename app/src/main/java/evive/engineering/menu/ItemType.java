package evive.engineering.menu;

public enum ItemType {
    MAIN("Main"),
    SIDE("Side"),
    DRINK("Drink"),
    DESSERT("Dessert");

    String groupName;
    ItemType(String name) {
        groupName = name;
    }

    @Override
    public String toString() {
        return groupName;
    }
}
