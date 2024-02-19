package Vendingmachine.exception;

public class InvalidProductId extends RuntimeException {


    public InvalidProductId (String message) {
        super(message);
    }

    public InvalidProductId (String message, Throwable cause) {
        super(message, cause);
    }
}
