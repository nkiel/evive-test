package evive.engineering.io;

import evive.engineering.Order;

// potentially could extend BufferedInputStream or similar class
public interface OrderingInput {
    public Order getNextOrder();
    public boolean hasNextOrder();
}
