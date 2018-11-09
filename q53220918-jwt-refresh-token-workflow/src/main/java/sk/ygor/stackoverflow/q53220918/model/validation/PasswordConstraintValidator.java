package sk.ygor.stackoverflow.q53220918.model.validation;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override public void initialize(ValidPassword constraintAnnotation) {
        // we could load a dictionary from classpath here
    }

    @Override public boolean isValid(String value, ConstraintValidatorContext context) {
        PasswordValidator passwordValidator = new PasswordValidator(Arrays.asList(
                // at least 8 characters
                new LengthRule(8, 1024),
                // at least one upper-case character
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                // at least one lower-case character
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                // at least one digit character
                new CharacterRule(EnglishCharacterData.Digit, 1),
                // no whitespace
                new WhitespaceRule()
        ));
        RuleResult result = passwordValidator.validate(new PasswordData(value));

        if (result.isValid()) {
            return true;
        } else {
            List<String> messages = passwordValidator.getMessages(result);
            String messageTemplate = String.join(",", messages);
            context.buildConstraintViolationWithTemplate(messageTemplate)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            return false;
        }

    }
}
