package cinema.controllers;

import cinema.dto.*;
import cinema.exceptions.SeatNotAvailableException;
import cinema.exceptions.SeatNotFoundException;
import cinema.exceptions.WrongPasswordException;
import cinema.exceptions.WrongTokenException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class RoomController {

    private final String SEAT_NOT_AVAILABLE_MESSAGE = "The ticket has been already purchased!";
    private final String SEAT_NOT_FOUND_MESSAGE = "The number of a row or a column is out of bounds!";
    private final String WRONG_TOKEN = "Wrong token!";
    private final String WRONG_PASSWORD = "The password is wrong!";
    private Room cinema = new Room(9, 9);
    private Till till = new Till();

    @GetMapping("/seats")
    public Room getSeats() {
        return cinema;
    }

    @GetMapping("/orders")
    public Till getOrders() {
        return till;
    }

    @PostMapping("/purchase")
    public Order getTicket(@RequestBody Seat requestedSeat) {
        List<Seat> availableSeats = cinema.getAvailableSeats();

        if (requestedSeat.getRow() > cinema.getTotalRows() ||
                requestedSeat.getColumn() > cinema.getTotalColumns() ||
                requestedSeat.getRow() < 1 || requestedSeat.getColumn() < 1) {
            throw new SeatNotFoundException(SEAT_NOT_FOUND_MESSAGE);
        }

        for (int i = 0; i < availableSeats.size(); i++) {
            Seat currentSeat = availableSeats.get(i);
            if (currentSeat.getColumn() == requestedSeat.getColumn()
                    && currentSeat.getRow() == requestedSeat.getRow()) {
                availableSeats.remove(i);
                Order order = new Order(currentSeat);
                till.getOrders().add(order);
                return order;
            }
        }
        throw new SeatNotAvailableException(SEAT_NOT_AVAILABLE_MESSAGE);
    }

    @PostMapping("/return")
    public ReturnedTicket returnTicket(@RequestBody Order orderNew) {
        Seat returnedTicket;
        List<Order> orders = till.getOrders();
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            if (orderNew.getToken().equals(order.getToken())) {
                returnedTicket = order.getTicket();
                orders.remove(order);
                cinema.getAvailableSeats().add(returnedTicket);
                return new ReturnedTicket(returnedTicket);
            }
        }
        throw new WrongTokenException(WRONG_TOKEN);
    }


    @PostMapping("/stats")
    public Stats getStats(@RequestParam(name = "password", required = false) String password) {
        try {
            if (password.equals("super_secret")) {
                List<Order> orders = till.getOrders();
                List<Seat> seats = cinema.getAvailableSeats();
                int currentIncome = 0;
                for (Order order : orders) {
                    currentIncome += order.getTicket().getPrice();
                }
                return new Stats(currentIncome, seats.size(), orders.size());
            }
            throw new WrongPasswordException(WRONG_PASSWORD);
        } catch (Exception e) {
            throw new WrongPasswordException(WRONG_PASSWORD);
        }
    }
}
