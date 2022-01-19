package pl.mariodev.creditapp.core.validation;

import pl.mariodev.creditapp.core.Constants;
import pl.mariodev.creditapp.core.exception.ValidationException;
import pl.mariodev.creditapp.core.model.CreditApplication;
import pl.mariodev.creditapp.core.model.PersonalData;

import static pl.mariodev.creditapp.core.Constants.*;

public class PersonalDataValidator implements Validator{
    @Override
    public void validate(CreditApplication creditApplication) throws ValidationException {
        PersonalData personalData = creditApplication.getPerson().getPersonalData();

        ValidationUtils.validateRegex("lastName", personalData.getLastName(), LAST_NAME_REGEX);
        ValidationUtils.validateRegex("name", personalData.getName(), NAME_REGEX);
        ValidationUtils.validateRegex("mothersMaidenName", personalData.getMothersMaidenName(), LAST_NAME_REGEX);

        ValidationUtils.validateNotNull("lastName", personalData.getLastName());
        ValidationUtils.validateNotNull("name", personalData.getName());
        ValidationUtils.validateNotNull("mothersMaidenName", personalData.getMothersMaidenName());
        ValidationUtils.validateNotNull("education", personalData.getEducation());
        ValidationUtils.validateNotNull("maritalStatus", personalData.getMaritalStatus());

    }
}
