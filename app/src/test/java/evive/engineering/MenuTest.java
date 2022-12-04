package evive.engineering;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import evive.engineering.menu.ItemType;
import evive.engineering.menu.Menu;
import evive.engineering.menu.MenuItem;

class MenuTest {
    static Menu testMenu;

    @BeforeAll
    static void prepareMenu() {
        testMenu = new Menu(new MenuItem("Pasta", ItemType.MAIN), new MenuItem("Garlic Bread", ItemType.SIDE), new MenuItem("Wine", ItemType.DRINK));
    }

    @Test
    void testValidate() {
        // no duplicates and no requirements
        assertEquals("Pasta, Garlic Bread, Water", testMenu.printOrder(new Order("TestBed", 1, 2), null, null));
        assertEquals("Garlic Bread, Wine", testMenu.printOrder(new Order("TestBed", 3, 2), null, null));
        assertEquals("Water", testMenu.printOrder(new Order("TestBed"), null, null));

        assertEquals("Pasta, Garlic Bread, Water", testMenu.printOrder(new Order("TestBed", 1, 2), null, new ItemType[]{ItemType.SIDE}));
        assertEquals("Pasta, Garlic Bread(3), Water", testMenu.printOrder(new Order("TestBed", 1, 2, 2, 2), null, new ItemType[]{ItemType.SIDE}));
        assertEquals("Pasta, Garlic Bread(2), Wine", testMenu.printOrder(new Order("TestBed", 1, 2, 2, 3), null, new ItemType[]{ItemType.SIDE}));

        assertEquals("Pasta, Garlic Bread, Water", testMenu.printOrder(new Order("TestBed", 1, 2), null, new ItemType[]{ItemType.SIDE, ItemType.DRINK}));
        assertEquals("Pasta, Garlic Bread, Wine(3)", testMenu.printOrder(new Order("TestBed", 1, 2, 3, 3, 3), null, new ItemType[]{ItemType.SIDE, ItemType.DRINK}));
        assertEquals("Pasta, Garlic Bread(2), Wine(4)", testMenu.printOrder(new Order("TestBed", 1, 2, 2, 3, 3, 3, 3), null, new ItemType[]{ItemType.SIDE, ItemType.DRINK}));
        
        assertEquals("Pasta(2), Garlic Bread, Water", testMenu.printOrder(new Order("TestBed", 1, 1, 2), null, new ItemType[]{ItemType.MAIN, ItemType.SIDE, ItemType.DRINK}));
        assertEquals("Pasta(4), Garlic Bread(2), Wine", testMenu.printOrder(new Order("TestBed", 1, 1, 1, 1, 2, 2, 3), null, new ItemType[]{ItemType.MAIN, ItemType.SIDE, ItemType.DRINK}));

        assertEquals("Pasta(2), Garlic Bread, Water", testMenu.printOrder(new Order("TestBed", 1, 1, 2), new ItemType[]{ItemType.MAIN}, new ItemType[]{ItemType.MAIN, ItemType.SIDE, ItemType.DRINK}));
        assertEquals("Pasta(4), Garlic Bread(3), Water", testMenu.printOrder(new Order("TestBed", 1, 1, 1, 1, 2, 2, 2), new ItemType[]{ItemType.MAIN}, new ItemType[]{ItemType.MAIN, ItemType.SIDE, ItemType.DRINK}));
    }

    @Test
    void outOfBoundsIds() {
        assertEquals("Unable to process: id 7 does not have a corrosponding item", testMenu.printOrder(new Order("TestBed", 1, 2, 3, 7)));
        assertEquals("Unable to process: id 11 does not have a corrosponding item", testMenu.printOrder(new Order("TestBed", 11, 1, 2, 3)));
        assertEquals("Unable to process: id -5 does not have a corrosponding item", testMenu.printOrder(new Order("TestBed", -5, 1, 2, 3)));
        assertEquals("Unable to process: id 1234 does not have a corrosponding item", testMenu.printOrder(new Order("TestBed", 1, 2, 3, 1234)));
    }
}
