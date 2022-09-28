package cinema.dto;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private int totalRows;
    private int totalColumns;
    private final List<Seat> availableSeats = new ArrayList<>();

    public Room(int totalRows, int totalColumns) {
        int price;
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        for (int i = 1; i <= totalColumns; i++) {
            for (int j = 1; j <= totalRows; j++) {
                price = j <= 4 ? 10 : 8;
                availableSeats.add(new Seat(j, i, price));
            }
        }
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }
}
