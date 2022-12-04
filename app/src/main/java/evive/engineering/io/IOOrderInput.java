package evive.engineering.io;

import java.io.BufferedInputStream;
import java.util.Scanner;

import evive.engineering.Order;

public class IOOrderInput implements OrderingInput {
    
    Scanner input;

    public IOOrderInput() {
        openInputStream();
    }
    
    private void openInputStream() {
        input = new Scanner(new BufferedInputStream(System.in));
    }

    public void clearInput() {
        input.close();
        openInputStream();
    }

    @Override
    protected void finalize() throws Throwable {
        input.close();
        super.finalize();
    }

    @Override
    public Order getNextOrder() {
        if(input.hasNext()) {
            Order nextOrder = new Order(input.next());
            for(String item : input.nextLine().split(",")) {
                nextOrder.addItem(Integer.parseInt(item));
            }
            return nextOrder;
        }
        return null;
    }

    @Override
    public boolean hasNextOrder() {
        return input.hasNext();
    }
}
