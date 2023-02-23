package at.spengergasse.rmbackend.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import graphql.com.google.common.base.Joiner;
import org.passay.*;

import java.util.Arrays;



public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword,String> {

    @Override
    public void initialize(ValidPassword validPassword) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
         PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 30),
                new UppercaseCharacterRule(1),
                new DigitCharacterRule(1),
                new SpecialCharacterRule(1),
                new NumericalSequenceRule(3,false),
                new AlphabeticalSequenceRule(3,false),
                new QwertySequenceRule(3,false),
                new WhitespaceRule()));

         RuleResult ruleResult = validator.validate(new PasswordData(s));

         if(ruleResult.isValid()){
             return true;
         }

         constraintValidatorContext.disableDefaultConstraintViolation();
         constraintValidatorContext.buildConstraintViolationWithTemplate(Joiner.on("").join(validator.getMessages(ruleResult))).addConstraintViolation();
         return false;
    }
}
