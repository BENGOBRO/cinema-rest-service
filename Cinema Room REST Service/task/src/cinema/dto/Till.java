package cinema.dto;

import java.util.ArrayList;
import java.util.List;

public class Till {
    private final List<Order> orders = new ArrayList<>();

    public Till() {
    }

    public List<Order> getOrders() {
        return orders;
    }
}
