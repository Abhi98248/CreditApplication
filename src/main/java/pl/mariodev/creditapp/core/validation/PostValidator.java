package pl.mariodev.creditapp.core.validation;

import pl.mariodev.creditapp.core.exception.RequirementNotMetException;
import pl.mariodev.creditapp.core.model.CreditApplication;

public interface PostValidator {

    void validate(CreditApplication creditApplication, int scoring, double rating) throws RequirementNotMetException;
}
