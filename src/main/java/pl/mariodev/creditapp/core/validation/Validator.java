package pl.mariodev.creditapp.core.validation;

import pl.mariodev.creditapp.core.exception.ValidationException;
import pl.mariodev.creditapp.core.model.CreditApplication;

public interface Validator {
    void validate(CreditApplication creditApplication) throws ValidationException;
}
