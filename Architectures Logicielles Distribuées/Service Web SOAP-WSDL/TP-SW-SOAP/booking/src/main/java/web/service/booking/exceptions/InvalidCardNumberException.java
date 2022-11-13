package web.service.booking.exceptions;

public class InvalidCardNumberException extends Exception {
    public InvalidCardNumberException() {
    }

    public InvalidCardNumberException(String message) {
        super(message);
    }
}
