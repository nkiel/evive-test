package evive.engineering;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedOutputStream;
import java.io.OutputStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import evive.engineering.io.IOOrderInput;

class OrderInputTest {

    static IOOrderInput input;
    static OutputStream ioOut;

    @BeforeAll
    static void ioSetup() {
        input = new IOOrderInput();
        ioOut = System.out;
    }

    @BeforeEach
    void ioCleanup() {
        input.clearInput();
    }

    @Test
    void breakfastMenuTestScan() {
        try {
            ioOut.write(("Breakfast 1,2,3").getBytes());
            Order nextOrder = input.getNextOrder();
            assertEquals(nextOrder.menu, "Breakfast");
            assertEquals(1, nextOrder.getItemCount(1));
            assertEquals(1, nextOrder.getItemCount(2));
            assertEquals(1, nextOrder.getItemCount(3));

            
            ioOut.write(("TestMenu 2,2,2").getBytes());
            nextOrder = input.getNextOrder();
            assertEquals(nextOrder.menu, "TestMenu");
            assertEquals(0, nextOrder.getItemCount(1));
            assertEquals(3, nextOrder.getItemCount(2));
            assertEquals(0, nextOrder.getItemCount(3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
