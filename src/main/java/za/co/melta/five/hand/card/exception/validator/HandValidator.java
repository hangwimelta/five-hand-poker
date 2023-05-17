package za.co.melta.five.hand.card.exception.validator;

import org.apache.logging.log4j.util.Strings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HandValidator implements ConstraintValidator<HandConstraint, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (Strings.isNotBlank(value)) {
            String[] cardsString = value.split(" ");
            return cardsString.length == 5;
        }
        return false;
    }
}
