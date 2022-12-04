package evive.engineering;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import evive.engineering.menu.DinnerMenu;
import evive.engineering.menu.Menu;

class DinnerMenuTest {
    static Menu testMenu;

    @BeforeAll
    static void prepareMenu() {
        testMenu = new DinnerMenu();
    }

    @Test
    void sampleOrdersTest() {
        assertEquals("Steak, Potatoes, Wine, Water, Cake", testMenu.printOrder(new Order("Dinner", 1, 2, 3, 4)));
        assertEquals("Unable to process: Dessert is missing", testMenu.printOrder(new Order("Dinner", 1, 2, 3)));
    }

    @Test
    void printOrderTest() {
        assertEquals("Steak, Potatoes, Wine, Water, Cake", testMenu.printOrder(new Order("Dinner", 1, 2, 3, 4)));
        assertEquals("Steak, Potatoes, Wine, Water, Cake", testMenu.printOrder(new Order("Dinner", 1, 2, 4, 3)));
        assertEquals("Steak, Potatoes, Wine, Water, Cake", testMenu.printOrder(new Order("Dinner", 1, 3, 2, 4)));
        assertEquals("Steak, Potatoes, Wine, Water, Cake", testMenu.printOrder(new Order("Dinner", 1, 3, 4, 2)));
        assertEquals("Steak, Potatoes, Wine, Water, Cake", testMenu.printOrder(new Order("Dinner", 1, 4, 2, 3)));
        assertEquals("Steak, Potatoes, Wine, Water, Cake", testMenu.printOrder(new Order("Dinner", 1, 4, 3, 2)));

        assertEquals("Steak, Potatoes, Wine, Water, Cake", testMenu.printOrder(new Order("Dinner", 2, 1, 3, 4)));
        assertEquals("Steak, Potatoes, Wine, Water, Cake", testMenu.printOrder(new Order("Dinner", 2, 1, 4, 3)));
        assertEquals("Steak, Potatoes, Wine, Water, Cake", testMenu.printOrder(new Order("Dinner", 2, 3, 1, 4)));
        assertEquals("Steak, Potatoes, Wine, Water, Cake", testMenu.printOrder(new Order("Dinner", 2, 3, 4, 1)));
        assertEquals("Steak, Potatoes, Wine, Water, Cake", testMenu.printOrder(new Order("Dinner", 2, 4, 1, 3)));
        assertEquals("Steak, Potatoes, Wine, Water, Cake", testMenu.printOrder(new Order("Dinner", 2, 4, 3, 1)));
        
        assertEquals("Steak, Potatoes, Wine, Water, Cake", testMenu.printOrder(new Order("Dinner", 3, 1, 2, 4)));
        assertEquals("Steak, Potatoes, Wine, Water, Cake", testMenu.printOrder(new Order("Dinner", 3, 1, 4, 2)));
        assertEquals("Steak, Potatoes, Wine, Water, Cake", testMenu.printOrder(new Order("Dinner", 3, 2, 1, 4)));
        assertEquals("Steak, Potatoes, Wine, Water, Cake", testMenu.printOrder(new Order("Dinner", 3, 2, 4, 1)));
        assertEquals("Steak, Potatoes, Wine, Water, Cake", testMenu.printOrder(new Order("Dinner", 3, 4, 1, 2)));
        assertEquals("Steak, Potatoes, Wine, Water, Cake", testMenu.printOrder(new Order("Dinner", 3, 4, 2, 1)));
        
        assertEquals("Steak, Potatoes, Wine, Water, Cake", testMenu.printOrder(new Order("Dinner", 4, 1, 2, 3)));
        assertEquals("Steak, Potatoes, Wine, Water, Cake", testMenu.printOrder(new Order("Dinner", 4, 1, 3, 2)));
        assertEquals("Steak, Potatoes, Wine, Water, Cake", testMenu.printOrder(new Order("Dinner", 4, 2, 1, 3)));
        assertEquals("Steak, Potatoes, Wine, Water, Cake", testMenu.printOrder(new Order("Dinner", 4, 2, 3, 1)));
        assertEquals("Steak, Potatoes, Wine, Water, Cake", testMenu.printOrder(new Order("Dinner", 4, 3, 1, 2)));
        assertEquals("Steak, Potatoes, Wine, Water, Cake", testMenu.printOrder(new Order("Dinner", 4, 3, 2, 1)));
    }

    @Test
    void drinklessTest() {
        assertEquals("Steak, Potatoes, Water, Cake", testMenu.printOrder(new Order("Dinner", 1, 2, 4)));
        assertEquals("Steak, Potatoes, Water, Cake", testMenu.printOrder(new Order("Dinner", 1, 4, 2)));
        assertEquals("Steak, Potatoes, Water, Cake", testMenu.printOrder(new Order("Dinner", 2, 1, 4)));
        assertEquals("Steak, Potatoes, Water, Cake", testMenu.printOrder(new Order("Dinner", 2, 4, 1)));
        assertEquals("Steak, Potatoes, Water, Cake", testMenu.printOrder(new Order("Dinner", 4, 1, 2)));
        assertEquals("Steak, Potatoes, Water, Cake", testMenu.printOrder(new Order("Dinner", 4, 2, 1)));

        assertEquals("Unable to process: Main is missing", testMenu.printOrder(new Order("Dinner", 2, 4)));
        assertEquals("Unable to process: Side is missing", testMenu.printOrder(new Order("Dinner", 1, 4)));
        assertEquals("Unable to process: Dessert is missing", testMenu.printOrder(new Order("Dinner", 1, 2)));

        assertEquals("Unable to process: Main is missing, Side is missing", testMenu.printOrder(new Order("Dinner", 4)));
        assertEquals("Unable to process: Main is missing, Dessert is missing", testMenu.printOrder(new Order("Dinner", 2)));
        assertEquals("Unable to process: Side is missing, Dessert is missing", testMenu.printOrder(new Order("Dinner", 1)));

        assertEquals("Unable to process: Main is missing, Side is missing, Dessert is missing", testMenu.printOrder(new Order("Dinner")));
    }

    @Test
    void duplicatesTest() {
        assertEquals("Unable to process: Steak cannot be ordered more than once", testMenu.printOrder(new Order("Dinner", 1, 1, 2, 3, 4)));
        assertEquals("Unable to process: Potatoes cannot be ordered more than once", testMenu.printOrder(new Order("Dinner", 1, 2, 2, 3, 4)));
        assertEquals("Unable to process: Wine cannot be ordered more than once", testMenu.printOrder(new Order("Dinner", 1, 2, 3, 3, 4)));
        assertEquals("Unable to process: Cake cannot be ordered more than once", testMenu.printOrder(new Order("Dinner", 1, 2, 3, 4, 4)));

        assertEquals("Unable to process: Steak cannot be ordered more than once, Potatoes cannot be ordered more than once", testMenu.printOrder(new Order("Dinner", 1, 1, 2, 2, 3, 4)));
        assertEquals("Unable to process: Steak cannot be ordered more than once, Wine cannot be ordered more than once", testMenu.printOrder(new Order("Dinner", 1, 1, 2, 3, 3, 4)));
        assertEquals("Unable to process: Steak cannot be ordered more than once, Cake cannot be ordered more than once", testMenu.printOrder(new Order("Dinner", 1, 1, 2, 3, 4, 4)));


        assertEquals("Unable to process: Steak cannot be ordered more than once, Potatoes cannot be ordered more than once, Wine cannot be ordered more than once", testMenu.printOrder(new Order("Dinner", 1, 1, 2, 2, 3, 3, 4)));

        assertEquals("Unable to process: Steak cannot be ordered more than once, Potatoes cannot be ordered more than once, Wine cannot be ordered more than once, Cake cannot be ordered more than once", testMenu.printOrder(new Order("Dinner", 1, 1, 2, 2, 3, 3, 4, 4)));
    
        assertEquals("Unable to process: Steak cannot be ordered more than once, Side is missing", testMenu.printOrder(new Order("Dinner", 1, 1, 3, 4)));
        assertEquals("Unable to process: Steak cannot be ordered more than once, Side is missing", testMenu.printOrder(new Order("Dinner", 1, 1, 4)));
        assertEquals("Unable to process: Steak cannot be ordered more than once, Side is missing, Dessert is missing", testMenu.printOrder(new Order("Dinner", 1, 1, 3)));
       
        assertEquals("Unable to process: Potatoes cannot be ordered more than once, Main is missing", testMenu.printOrder(new Order("Dinner", 2, 2, 3, 4)));
        assertEquals("Unable to process: Cake cannot be ordered more than once, Main is missing, Side is missing", testMenu.printOrder(new Order("Dinner", 4, 4)));
        
    }
}
