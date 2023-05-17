package za.co.melta.five.hand.card.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PokerHandException extends RuntimeException {
    final HttpStatus status;

    public PokerHandException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
