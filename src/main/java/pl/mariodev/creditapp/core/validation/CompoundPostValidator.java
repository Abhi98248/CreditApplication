package pl.mariodev.creditapp.core.validation;

import pl.mariodev.creditapp.core.exception.RequirementNotMetException;
import pl.mariodev.creditapp.core.model.CreditApplication;

public class CompoundPostValidator implements PostValidator {

    private final PostValidator[] postValidators;

    public CompoundPostValidator(PostValidator... postValidators) {
        this.postValidators = postValidators;
    }

    @Override
    public void validate(CreditApplication creditApplication, int scoring, double rating) throws RequirementNotMetException {
        for(PostValidator postValidator : postValidators) {
            postValidator.validate(creditApplication, scoring, rating);
        }
    }
}
