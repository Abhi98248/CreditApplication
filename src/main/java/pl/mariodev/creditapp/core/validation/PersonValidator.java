package pl.mariodev.creditapp.core.validation;

import pl.mariodev.creditapp.core.exception.ValidationException;
import pl.mariodev.creditapp.core.model.CreditApplication;

public class PersonValidator implements Validator{
    private final  PersonalDataValidator personalDataValidator;

    public PersonValidator(PersonalDataValidator personalDataValidator) {
        this.personalDataValidator = personalDataValidator;
    }

    @Override
    public void validate(CreditApplication creditApplication) throws ValidationException {
        ValidationUtils.validateNotNull("peronalData", creditApplication.getPerson().getPersonalData());
        personalDataValidator.validate(creditApplication);

        //validate contactData
        //validate financeData
    }
}
