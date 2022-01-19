package pl.mariodev.creditapp.core.validation;

import pl.mariodev.creditapp.core.exception.MinValueException;
import pl.mariodev.creditapp.core.exception.NotNullException;
import pl.mariodev.creditapp.core.exception.RegexException;
import pl.mariodev.creditapp.core.exception.ValidationException;

public class ValidationUtils {

    public static void validateNotNull(String field, Object object) throws ValidationException {
        if(object == null) {
            throw new NotNullException(field);
        }
    }

    public static void validateRegex(String field, String value, String regex) throws ValidationException {
        if(!value.matches(regex)) {
            throw new RegexException(field);
        }
    }

    public static void validateMinValue(String field, int extMinValue, int actuallyValue) throws ValidationException {
        if(actuallyValue <= extMinValue) {
            throw new MinValueException(field, extMinValue);
        }
    }

    public static void validateMinValue(String field, double extMinValue, double actuallyValue) throws ValidationException {
        if(actuallyValue <= extMinValue) {
            throw new MinValueException(field, extMinValue);
        }
    }

    public static void validateMaxValue(String field, int extMaxValue, int actuallyValue) throws ValidationException {
        if(actuallyValue >= extMaxValue) {
            throw new MinValueException(field, extMaxValue);
        }
    }

    public static void validateMaxValue(String field, double extMaxValue, double actuallyValue) throws ValidationException {
        if(actuallyValue >= extMaxValue) {
            throw new MinValueException(field, extMaxValue);
        }
    }
}
