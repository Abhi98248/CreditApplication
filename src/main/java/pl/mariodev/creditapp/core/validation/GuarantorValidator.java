package pl.mariodev.creditapp.core.validation;

import pl.mariodev.creditapp.core.Constants;
import pl.mariodev.creditapp.core.exception.ValidationException;
import pl.mariodev.creditapp.core.model.CreditApplication;
import pl.mariodev.creditapp.core.model.Guarantor;
import pl.mariodev.creditapp.core.model.PurposeOfLoan;

import java.util.Set;

import static pl.mariodev.creditapp.core.Constants.*;

public class GuarantorValidator implements Validator{
    @Override
    public void validate(CreditApplication creditApplication) throws ValidationException {
        final Set<Guarantor> guarantors = creditApplication.getGuarantors();
        for (Guarantor g : guarantors) {
            ValidationUtils.validateNotNull("guarantorPesel", g.getPesel());
            ValidationUtils.validateRegex("guarantorPesel", g.getPesel(), PESEL_REGEX);
            ValidationUtils.validateMinValue("guarantorAge", 0, g.getAge());
        }

    }
}
