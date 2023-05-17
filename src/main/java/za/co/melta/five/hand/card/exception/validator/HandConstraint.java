package za.co.melta.five.hand.card.exception.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HandValidator.class)
public @interface HandConstraint {
    String message() default "Invalid poker hand captured. Format should be XY XY XY XY XY e.g JH 9S TH AS KS";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
