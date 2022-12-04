package evive.engineering;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MenuSystemTest {

    static MenuSystem mySystem;

    @BeforeAll
    static void systemSetup() {
        mySystem = new MenuSystem();
    }
    
    @Test
    void testMenus() {
        assertEquals("Unable to process: System does not contain a menu called TestMenu", mySystem.validateOrder(new Order("TestMenu", 0)));
        assertEquals("Unable to process: System does not contain a menu called TestMenu", mySystem.printOrder(new Order("TestMenu", 0)));
    }
}
