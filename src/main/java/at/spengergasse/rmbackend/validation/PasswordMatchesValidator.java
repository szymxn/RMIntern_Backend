package at.spengergasse.rmbackend.validation;

import at.spengergasse.rmbackend.service.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches passwordMatches) {
        System.out.println("Hi");
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        UserDto userDto = (UserDto) o;
        return false;
    }
}
