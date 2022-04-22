package org.grube.bookstoreapispring.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class Isbn13Validator implements ConstraintValidator<Isbn13Constraint, String> {

    @Override
    public void initialize(Isbn13Constraint isbn13Constraint) {

    }

    @Override
    public boolean isValid(String input, ConstraintValidatorContext constraintValidatorContext) {
        if (input == null || input.length() != 13) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("isbn must be 13 digits string").addConstraintViolation();
            return false;
        }
        String isbn12 = input.substring(0, input.length() - 1);
        String checkDigit = String.valueOf(isbn13CheckDigit(isbn12));
        String lastDigit = input.substring(input.length() - 1);
        if (!checkDigit.equals(lastDigit)) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("invalid check digit").addConstraintViolation();
            return false;
        }
        return true;
    }

    // Calculates the isbn13 check digit for the 1st 12 digits in the string.
    private char isbn13CheckDigit(String str) {
        // Sum of the 12 digits.
        int sum = 0;
        // Digits counted.
        int digits = 0;
        // Start multiplier at 1. Alternates between 1 and 3.
        int multiplier = 1;
        // Treat just the 1st 12 digits of the string.
        for (int i = 0; i < str.length() && digits < 12; i++) {
            // Pull out that character.
            char c = str.charAt(i);
            // Is it a digit?
            if ('0' <= c && c <= '9') {
                // Keep the sum.
                sum += multiplier * (c - '0');
                // Flip multiplier between 1 and 3 by flipping the 2^1 bit.
                multiplier ^= 2;
                // Count the digits.
                digits += 1;
            }
        }
        // What is the check digit?
        int checkDigit = (10 - (sum % 10)) % 10;
        // Give it back to them in character form.
        return (char) (checkDigit + '0');
    }
}


