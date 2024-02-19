package Vendingmachine.exception;


public class InsufficientInputCash extends RuntimeException {


    public InsufficientInputCash(String message) {
        super(message);
    }

    public InsufficientInputCash(String message, Throwable cause) {
       super(message, cause);
    }
}
