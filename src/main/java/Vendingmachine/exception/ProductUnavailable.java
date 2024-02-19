package Vendingmachine.exception;

public class ProductUnavailable extends RuntimeException{

    public ProductUnavailable(String message) {
        super(message);
    }
//throwable is super class of all exceptions
    public ProductUnavailable(String message, Throwable cause) {
        super(message, cause);
    }
}
