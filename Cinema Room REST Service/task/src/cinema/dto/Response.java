package cinema.dto;

public class Response {
    private String error;

    public Response() {
    }

    public Response(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String message) {
        this.error = message;
    }
}
