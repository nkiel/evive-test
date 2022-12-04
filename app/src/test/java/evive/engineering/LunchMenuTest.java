package evive.engineering;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import evive.engineering.menu.LunchMenu;
import evive.engineering.menu.Menu;

class LunchMenuTest {
    static Menu testMenu;

    @BeforeAll
    static void prepareMenu() {
        testMenu = new LunchMenu();
    }

    @Test
    void sampleOrdersTest() {
        assertEquals("Sandwich, Chips, Soda", testMenu.printOrder(new Order("Lunch", 1, 2, 3)));
        assertEquals("Sandwich, Chips, Water", testMenu.printOrder(new Order("Lunch", 1, 2)));
        assertEquals("Unable to process: Sandwich cannot be ordered more than once", testMenu.printOrder(new Order("Lunch", 1, 1, 2, 3)));
        assertEquals("Sandwich, Chips(2), Water", testMenu.printOrder(new Order("Lunch", 1, 2, 2)));
        assertEquals("Unable to process: Main is missing, Side is missing", testMenu.printOrder(new Order("Lunch")));
    }

    @Test
    void printOrderTest() {
        assertEquals("Sandwich, Chips, Soda", testMenu.printOrder(new Order("Lunch", 1, 2, 3)));
        assertEquals("Sandwich, Chips, Soda", testMenu.printOrder(new Order("Lunch", 1, 3, 2)));
        assertEquals("Sandwich, Chips, Soda", testMenu.printOrder(new Order("Lunch", 2, 1, 3)));
        assertEquals("Sandwich, Chips, Soda", testMenu.printOrder(new Order("Lunch", 2, 3, 1)));
        assertEquals("Sandwich, Chips, Soda", testMenu.printOrder(new Order("Lunch", 3, 1, 2)));
        assertEquals("Sandwich, Chips, Soda", testMenu.printOrder(new Order("Lunch", 3, 2, 1)));

        assertEquals("Sandwich, Chips(2), Soda", testMenu.printOrder(new Order("Lunch", 1, 2, 2, 3)));
        assertEquals("Sandwich, Chips(2), Soda", testMenu.printOrder(new Order("Lunch", 2, 1, 2, 3)));
        assertEquals("Sandwich, Chips(2), Soda", testMenu.printOrder(new Order("Lunch", 1, 2, 3, 2)));

        assertEquals("Sandwich, Chips(4), Soda", testMenu.printOrder(new Order("Lunch", 1, 2, 2, 3, 2, 2)));
        assertEquals("Sandwich, Chips(3), Soda", testMenu.printOrder(new Order("Lunch", 1, 2, 3, 2, 2)));
    }

    @Test
    void drinklessTest() {
        assertEquals("Sandwich, Chips, Water", testMenu.printOrder(new Order("Lunch", 1, 2)));
        assertEquals("Sandwich, Chips, Water", testMenu.printOrder(new Order("Lunch", 2, 1)));

        assertEquals("Sandwich, Chips(4), Water", testMenu.printOrder(new Order("Lunch", 1, 2, 2, 2, 2)));
        assertEquals("Sandwich, Chips(2), Water", testMenu.printOrder(new Order("Lunch", 2, 1, 2)));

        assertEquals("Unable to process: Main is missing", testMenu.printOrder(new Order("Lunch", 2)));
        assertEquals("Unable to process: Side is missing", testMenu.printOrder(new Order("Lunch", 1)));

        assertEquals("Unable to process: Main is missing, Side is missing", testMenu.printOrder(new Order("Lunch")));
        assertEquals("Unable to process: Main is missing, Side is missing", testMenu.printOrder(new Order("Lunch", 3)));
    }

    @Test
    void duplicatesTest() {
        assertEquals("Unable to process: Sandwich cannot be ordered more than once", testMenu.printOrder(new Order("Lunch", 1, 1, 2, 3)));
        assertEquals("Unable to process: Soda cannot be ordered more than once", testMenu.printOrder(new Order("Lunch", 1, 2, 3, 3)));
        assertEquals("Unable to process: Sandwich cannot be ordered more than once, Soda cannot be ordered more than once", testMenu.printOrder(new Order("Lunch", 1, 1, 2, 3, 3)));
        assertEquals("Unable to process: Sandwich cannot be ordered more than once, Soda cannot be ordered more than once", testMenu.printOrder(new Order("Lunch", 1, 1, 2, 2, 3, 3)));
    }
}
