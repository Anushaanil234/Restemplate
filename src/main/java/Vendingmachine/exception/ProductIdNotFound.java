package Vendingmachine.exception;//package Vendingmachine.exception;

public class ProductIdNotFound extends RuntimeException{

    public ProductIdNotFound(String message) {
        super(message);
    }
//throwable is super class of all exceptions
    public ProductIdNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
