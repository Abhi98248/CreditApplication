package pl.mariodev.creditapp.core.validation;

import pl.mariodev.creditapp.core.exception.RequirementNotMetException;
import pl.mariodev.creditapp.core.model.CreditApplication;
import pl.mariodev.creditapp.core.model.ExpenseType;

import static pl.mariodev.creditapp.core.Constants.MIN_LOAN_AMOUNT_MORTGAGE;
import static pl.mariodev.creditapp.core.exception.RequirementNotMetCause.TOO_HIGH_EXPENSES;
import static pl.mariodev.creditapp.core.exception.RequirementNotMetCause.TOO_LOW_DOWN_AMOUNT;
import static pl.mariodev.creditapp.core.model.ExpenseType.PERSONAL;

public class ExpansePostValidator implements PostValidator{
    @Override
    public void validate(CreditApplication creditApplication, int scoring, double rating) throws RequirementNotMetException {
        double balance = creditApplication.getPerson().getBalance();
        double personalExpanses = creditApplication.getPerson().getFinanceData().getSumOfExpenses(PERSONAL);

        double percentage = personalExpanses * 100 / balance;

        if (percentage > 40) {
            throw new RequirementNotMetException(TOO_HIGH_EXPENSES);
        }
    }
}
