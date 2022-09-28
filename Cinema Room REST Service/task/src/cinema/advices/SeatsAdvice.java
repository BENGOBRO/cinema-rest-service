package cinema.advices;

import cinema.dto.Response;
import cinema.exceptions.SeatNotAvailableException;
import cinema.exceptions.SeatNotFoundException;
import cinema.exceptions.WrongPasswordException;
import cinema.exceptions.WrongTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SeatsAdvice {

    @ExceptionHandler(SeatNotAvailableException.class)
    public ResponseEntity<Response> handleSeatNotAvailableException(SeatNotAvailableException error) {
        Response response = new Response(error.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SeatNotFoundException.class)
    public ResponseEntity<Response> handleSeatNotFoundException(SeatNotFoundException error) {
        Response response = new Response(error.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongTokenException.class)
    public ResponseEntity<Response> handleWrongTokenException(WrongTokenException error) {
        Response response = new Response(error.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<Response> handleWrongPasswordException(WrongPasswordException error) {
        Response response = new Response(error.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
