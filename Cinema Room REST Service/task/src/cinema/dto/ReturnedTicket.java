package cinema.dto;

public class ReturnedTicket {
    private Seat returnedTicket;

    public ReturnedTicket() {
    }

    public ReturnedTicket(Seat returnedTicket) {
        this.returnedTicket = returnedTicket;
    }

    public Seat getReturnedTicket() {
        return returnedTicket;
    }

    public void setReturnedTicket(Seat returnedTicket) {
        this.returnedTicket = returnedTicket;
    }
}
