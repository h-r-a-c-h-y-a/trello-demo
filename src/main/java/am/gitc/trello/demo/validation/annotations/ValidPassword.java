package am.gitc.trello.demo.validation.annotations;

import am.gitc.trello.demo.validation.constraints.PasswordConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {

    String message() default "Password must be equal to or greater than 6 characters\" +\n" +
            "            \" and less than 20 characters and contains minimum 1 special, lowercase and  digits.";
    Class<? extends Payload>[] payload() default {};
}
