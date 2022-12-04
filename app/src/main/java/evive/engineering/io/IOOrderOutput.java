package evive.engineering.io;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class IOOrderOutput implements OrderingOutput {

    @Override
    public void printOrder(String order) {
        System.out.println(order);
    }
}
