package pl.mariodev.creditapp.core.validation;

import pl.mariodev.creditapp.core.exception.ValidationException;
import pl.mariodev.creditapp.core.model.CreditApplication;
import pl.mariodev.creditapp.core.model.PurposeOfLoan;

public class PurposeOfLoanValidator implements Validator{
    @Override
    public void validate(CreditApplication creditApplication) throws ValidationException {
        final PurposeOfLoan purposeOfLoan = creditApplication.getPurposeOfLoan();

        ValidationUtils.validateNotNull("purposeOfLoanType", purposeOfLoan.getPurposeOfLoanType());
        ValidationUtils.validateMinValue("purposeOfLoanAmount", 0.0, purposeOfLoan.getAmount());
    }
}
