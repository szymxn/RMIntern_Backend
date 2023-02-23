package at.spengergasse.rmbackend.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidater implements ConstraintValidator<ValidEmail, String> {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN);

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext constraintValidatorContext) {
        return (validateEmail(userName));
    }

    private boolean validateEmail(final String email){
        Matcher matcher = PATTERN.matcher(email);
        return matcher.matches();
    }
}
