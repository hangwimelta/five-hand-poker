package za.co.melta.five.hand.card.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import za.co.melta.five.hand.card.exception.validator.Problem;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(PokerHandException.class)
    public ResponseEntity<String> handleServerError(PokerHandException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        var problem = new Problem().setMessage(ex.getMessage()).setStatusCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(problem);
    }
}
