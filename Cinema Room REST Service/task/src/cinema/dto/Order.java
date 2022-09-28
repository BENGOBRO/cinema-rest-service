package cinema.dto;

import java.util.UUID;

public class Order {
    private UUID token;
    private Seat ticket;

    public Order() {
    }

    public Order(Seat ticket) {
        token = UUID.randomUUID();
        this.ticket = ticket;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order ticket = (Order) o;
        return token.equals(ticket.token) && this.ticket.equals(ticket.ticket);
    }
}
