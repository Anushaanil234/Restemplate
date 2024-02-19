//package Vendingmachine.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//
//@ControllerAdvice
//public class GlobalException {
//
//    @ExceptionHandler
//    public ResponseEntity<VendingMachineException> codeNotFoundExceptionController(ProductIdNotFound productIdNotFound){
//
//        VendingMachineException vendingMachineException = new VendingMachineException(
//                productIdNotFound.getMessage(),
//                HttpStatus.NOT_FOUND); //suitable httpstatus can be kept
//        return new ResponseEntity<>(vendingMachineException,HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<VendingMachineException> productUnavialiableExceptionController(ProductUnavailable  productUnavailable ){
//
//        VendingMachineException vendingMachineException = new VendingMachineException(
//                productUnavailable .getMessage(),
//                HttpStatus.BAD_REQUEST); //suitable httpstatus can be kept
//        return new ResponseEntity<>(vendingMachineException,HttpStatus.BAD_REQUEST);
//    }
//
//
//    @ExceptionHandler(value = {InsufficientInitialBalance.class})
//    public ResponseEntity<VendingMachineException> InsufficientAmountExceptionHandler(InsufficientInitialBalance insufficientInitialBalance) {
//
//        VendingMachineException vendingMachineException = new VendingMachineException(
//                insufficientInitialBalance.getMessage(),
//                HttpStatus.BAD_REQUEST);
//        return new ResponseEntity<>(vendingMachineException, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(value = {InsufficientInputCash.class})
//    public ResponseEntity<VendingMachineException> InsufficientChangeExceptionHandler(InsufficientInputCash insufficientInputCash) {
//
//        VendingMachineException vendingMachineException = new VendingMachineException(
//                insufficientInputCash.getMessage(),
//                //this is server side error so changed to errore from bad request to  http code 500
//                HttpStatus.BAD_REQUEST);
//        return new ResponseEntity<>(vendingMachineException, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(value = {InvalidProductId.class})
//    public ResponseEntity<VendingMachineException> invalidProductIdExceptionHandler(InvalidProductId invalidProductId) {
//        VendingMachineException vendingMachineException = new VendingMachineException(
//                invalidProductId.getMessage(),
//                HttpStatus.BAD_REQUEST); // or choose an appropriate HttpStatus
//
//        return new ResponseEntity<>(vendingMachineException, HttpStatus.BAD_REQUEST);
//    }
//
//
////}
package Vendingmachine.exception;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Configuration
public class ExceptionHandlingConfig {

    @ControllerAdvice
    public static class GlobalException {

        @ExceptionHandler
        public ResponseEntity<VendingMachineException> codeNotFoundExceptionController(ProductIdNotFound productIdNotFound) {
            VendingMachineException vendingMachineException = new VendingMachineException(
                    productIdNotFound.getMessage(),
                    HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(vendingMachineException, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler
        public ResponseEntity<VendingMachineException> productUnavailableExceptionController(ProductUnavailable productUnavailable) {
            VendingMachineException vendingMachineException = new VendingMachineException(
                    productUnavailable.getMessage(),
                    HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(vendingMachineException, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(value = {InsufficientInitialBalance.class})
        public ResponseEntity<VendingMachineException> InsufficientAmountExceptionHandler(InsufficientInitialBalance insufficientInitialBalance) {

            VendingMachineException vendingMachineException = new VendingMachineException(
                    insufficientInitialBalance.getMessage(),
                    HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(vendingMachineException, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(value = {InsufficientInputCash.class})
        public ResponseEntity<VendingMachineException> InsufficientChangeExceptionHandler(InsufficientInputCash insufficientInputCash) {

            VendingMachineException vendingMachineException = new VendingMachineException(
                    insufficientInputCash.getMessage(),
                    HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(vendingMachineException, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(value = {InvalidProductId.class})
        public ResponseEntity<VendingMachineException> invalidProductIdExceptionHandler(InvalidProductId invalidProductId) {
            VendingMachineException vendingMachineException = new VendingMachineException(
                    invalidProductId.getMessage(),
                    HttpStatus.BAD_REQUEST); // or choose an appropriate HttpStatus

            return new ResponseEntity<>(vendingMachineException, HttpStatus.BAD_REQUEST);
        }


//}

    }
}
