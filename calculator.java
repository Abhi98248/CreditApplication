package pl.mariodev.creditapp.core;

import pl.mariodev.creditapp.core.model.NaturalPerson;
import pl.mariodev.creditapp.core.model.Person;
import pl.mariodev.creditapp.core.model.SelfEmployed;
import pl.mariodev.creditapp.core.scoring.*;

public class PersonScoringCalculatorFactory {
    private final SelfEmployedScoringCalculator selfEmployedScoringCalculator;
    private final EducationCalculator educationCalculator;
    private final IncomeCalculator incomeCalculator;
    private final MartialStatusCalculator martialStatusCalculator;
    private final GuarantorsCalculator guarantorsCalculator;

    public PersonScoringCalculatorFactory(SelfEmployedScoringCalculator selfEmployedScoringCalculator, EducationCalculator educationCalculator, IncomeCalculator incomeCalculator, MartialStatusCalculator martialStatusCalculator, GuarantorsCalculator guarantorsCalculator) {
        this.selfEmployedScoringCalculator = selfEmployedScoringCalculator;
        this.educationCalculator = educationCalculator;
        this.incomeCalculator = incomeCalculator;
        this.martialStatusCalculator = martialStatusCalculator;
        this.guarantorsCalculator = guarantorsCalculator;
    }

    public ScoringCalculator getCalculator(Person person) {
        if (person instanceof NaturalPerson) {
            return new CompoundScoringCalculator(guarantorsCalculator, educationCalculator, incomeCalculator, martialStatusCalculator);
        } else if (person instanceof SelfEmployed) {
            return new CompoundScoringCalculator(guarantorsCalculator, educationCalculator, incomeCalculator, martialStatusCalculator, selfEmployedScoringCalculator);
        }
        return null;
    }
}
