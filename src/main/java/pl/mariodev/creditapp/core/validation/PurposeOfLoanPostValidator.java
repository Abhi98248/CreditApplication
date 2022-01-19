package pl.mariodev.creditapp.core.validation;

import pl.mariodev.creditapp.core.CreditApplicationDecision;
import pl.mariodev.creditapp.core.exception.RequirementNotMetCause;
import pl.mariodev.creditapp.core.exception.RequirementNotMetException;
import pl.mariodev.creditapp.core.model.CreditApplication;

import static pl.mariodev.creditapp.core.Constants.MIN_LOAN_AMOUNT_MORTGAGE;
import static pl.mariodev.creditapp.core.DecisionType.NEGATIVE_REQUIREMENTS_NOT_MET;
import static pl.mariodev.creditapp.core.exception.RequirementNotMetCause.TOO_LOW_DOWN_AMOUNT;

public class PurposeOfLoanPostValidator implements PostValidator{
    @Override
    public void validate(CreditApplication creditApplication, int scoring, double rating) throws RequirementNotMetException {
        if (creditApplication.getPurposeOfLoan().getAmount() < MIN_LOAN_AMOUNT_MORTGAGE) {
            throw new RequirementNotMetException(TOO_LOW_DOWN_AMOUNT);
        }
    }
}
