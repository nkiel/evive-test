package evive.engineering;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import evive.engineering.menu.BreakfastMenu;
import evive.engineering.menu.Menu;

class BreakfastMenuTest {
    static Menu testMenu;

    @BeforeAll
    static void prepareMenu() {
        testMenu = new BreakfastMenu();
    }

    @Test
    void sampleOrdersTest() {
        assertEquals("Eggs, Toast, Coffee", testMenu.printOrder(new Order("Breakfast", 1, 2, 3)));
        assertEquals("Eggs, Toast, Coffee", testMenu.printOrder(new Order("Breakfast", 2, 3, 1)));
        assertEquals("Eggs, Toast, Coffee(3)", testMenu.printOrder(new Order("Breakfast", 1, 2, 3, 3, 3)));
        assertEquals("Unable to process: Side is missing", testMenu.printOrder(new Order("Breakfast", 1)));
    }

    @Test
    void printOrderTest() {
        assertEquals("Eggs, Toast, Coffee", testMenu.printOrder(new Order("Breakfast", 1, 2, 3)));
        assertEquals("Eggs, Toast, Coffee", testMenu.printOrder(new Order("Breakfast", 1, 3, 2)));
        assertEquals("Eggs, Toast, Coffee", testMenu.printOrder(new Order("Breakfast", 2, 1, 3)));
        assertEquals("Eggs, Toast, Coffee", testMenu.printOrder(new Order("Breakfast", 2, 3, 1)));
        assertEquals("Eggs, Toast, Coffee", testMenu.printOrder(new Order("Breakfast", 3, 1, 2)));
        assertEquals("Eggs, Toast, Coffee", testMenu.printOrder(new Order("Breakfast", 3, 2, 1)));

        assertEquals("Eggs, Toast, Coffee(2)", testMenu.printOrder(new Order("Breakfast", 1, 2, 3, 3)));
        assertEquals("Eggs, Toast, Coffee(2)", testMenu.printOrder(new Order("Breakfast", 1, 3, 2, 3)));
        assertEquals("Eggs, Toast, Coffee(2)", testMenu.printOrder(new Order("Breakfast", 3, 1, 2, 3)));

        assertEquals("Eggs, Toast, Coffee(2)", testMenu.printOrder(new Order("Breakfast", 3, 3, 2, 1)));
        assertEquals("Eggs, Toast, Coffee(2)", testMenu.printOrder(new Order("Breakfast", 3, 2, 3, 1)));
        assertEquals("Eggs, Toast, Coffee(2)", testMenu.printOrder(new Order("Breakfast", 3, 2, 1, 3)));
        
    }

    @Test
    void drinklessTest() {
        assertEquals("Eggs, Toast, Water", testMenu.printOrder(new Order("Breakfast", 1, 2)));
        assertEquals("Eggs, Toast, Water", testMenu.printOrder(new Order("Breakfast", 2, 1)));

        assertEquals("Unable to process: Side is missing", testMenu.printOrder(new Order("Breakfast", 1)));
        assertEquals("Unable to process: Main is missing", testMenu.printOrder(new Order("Breakfast", 2)));

        assertEquals("Unable to process: Side is missing", testMenu.printOrder(new Order("Breakfast", 1, 3)));
        assertEquals("Unable to process: Main is missing", testMenu.printOrder(new Order("Breakfast", 2, 3)));

        assertEquals("Unable to process: Main is missing, Side is missing",
                testMenu.printOrder(new Order("Breakfast")));
    }

    @Test
    void duplicatesTest() {
        assertEquals("Eggs, Toast, Coffee(5)", testMenu.printOrder(new Order("Breakfast", 1, 2, 3, 3, 3, 3, 3)));

        assertEquals("Unable to process: Eggs cannot be ordered more than once",
                testMenu.printOrder(new Order("Breakfast", 1, 1, 2, 3)));
        assertEquals("Unable to process: Eggs cannot be ordered more than once",
                testMenu.printOrder(new Order("Breakfast", 1, 1, 2)));

        assertEquals("Unable to process: Toast cannot be ordered more than once",
                testMenu.printOrder(new Order("Breakfast", 1, 2, 2, 3)));
        assertEquals("Unable to process: Toast cannot be ordered more than once",
                testMenu.printOrder(new Order("Breakfast", 1, 2, 2)));

        assertEquals("Unable to process: Eggs cannot be ordered more than once, Toast cannot be ordered more than once",
                testMenu.printOrder(new Order("Breakfast", 1, 1, 2, 2)));
        assertEquals("Unable to process: Eggs cannot be ordered more than once, Toast cannot be ordered more than once",
                testMenu.printOrder(new Order("Breakfast", 1, 1, 2, 2, 3)));

        assertEquals("Unable to process: Eggs cannot be ordered more than once, Side is missing",
                testMenu.printOrder(new Order("Breakfast", 1, 1, 3)));
        assertEquals("Unable to process: Eggs cannot be ordered more than once, Side is missing",
                testMenu.printOrder(new Order("Breakfast", 1, 1)));

        assertEquals("Unable to process: Toast cannot be ordered more than once, Main is missing",
                testMenu.printOrder(new Order("Breakfast", 2, 2, 3)));
        assertEquals("Unable to process: Toast cannot be ordered more than once, Main is missing",
                testMenu.printOrder(new Order("Breakfast", 2, 2)));
    }

    @Test
    void outOfBoundsIds() {
        assertEquals("Unable to process: id 4 does not have a corrosponding item", testMenu.printOrder(new Order("Breakfast", 1, 2, 3, 4)));
        assertEquals("Unable to process: id 0 does not have a corrosponding item", testMenu.printOrder(new Order("Breakfast", 0, 1, 2, 3)));
        assertEquals("Unable to process: id -1 does not have a corrosponding item", testMenu.printOrder(new Order("Breakfast", -1, 1, 2, 3)));
        assertEquals("Unable to process: id 67 does not have a corrosponding item", testMenu.printOrder(new Order("Breakfast", 1, 2, 3, 67)));
    }
}
