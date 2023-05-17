package za.co.melta.five.hand.card.exception.validator;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Problem {
    String message;
    int statusCode;
}
