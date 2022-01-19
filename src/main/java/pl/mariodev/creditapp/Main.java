package pl.mariodev.creditapp;


import pl.mariodev.creditapp.client.CreditApplicationReader;
import pl.mariodev.creditapp.client.DummyCreditApplicationReader;
import pl.mariodev.creditapp.core.*;
import pl.mariodev.creditapp.core.scoring.EducationCalculator;
import pl.mariodev.creditapp.core.scoring.GuarantorsCalculator;
import pl.mariodev.creditapp.core.scoring.IncomeCalculator;
import pl.mariodev.creditapp.core.scoring.MartialStatusCalculator;
import pl.mariodev.creditapp.core.validation.*;

public class Main {

    public static void main(String[] args) {
        CreditApplicationReader reader = new DummyCreditApplicationReader();
        EducationCalculator educationCalculator = new EducationCalculator();
        IncomeCalculator incomeCalculator = new IncomeCalculator();
        MartialStatusCalculator martialStatusCalculator = new MartialStatusCalculator();
        SelfEmployedScoringCalculator selfEmployedScoringCalculator = new SelfEmployedScoringCalculator();
        GuarantorsCalculator guarantorsCalculator = new GuarantorsCalculator();
        GuarantorValidator guarantorValidator = new GuarantorValidator();
        PersonScoringCalculatorFactory personScoringCalculatorFactory = new PersonScoringCalculatorFactory(selfEmployedScoringCalculator, educationCalculator, incomeCalculator, martialStatusCalculator, guarantorsCalculator);
        CreditApplicationValidator creditApplicationValidator = new CreditApplicationValidator(new PersonValidator(new PersonalDataValidator()), new PurposeOfLoanValidator(), guarantorValidator);
        CompoundPostValidator compoundPostValidator = new CompoundPostValidator(new PurposeOfLoanPostValidator(), new ExpansePostValidator());
        CreditApplicationService service = new CreditApplicationService(personScoringCalculatorFactory, new CreditRatingCalculator(), creditApplicationValidator, compoundPostValidator);
        CreditApplicationManager manager = new CreditApplicationManager(service);

        manager.add(reader.read());


        manager.startProcessing();
    }
}
