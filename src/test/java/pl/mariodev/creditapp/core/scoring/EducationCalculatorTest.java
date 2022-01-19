package pl.mariodev.creditapp.core.scoring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import pl.mariodev.creditapp.core.model.Education;
import pl.mariodev.creditapp.core.model.MaritalStatus;
import pl.mariodev.creditapp.core.model.Person;
import pl.mariodev.creditapp.core.model.PersonTestFactory;

import static org.junit.jupiter.api.Assertions.*;

class EducationCalculatorTest {
    private EducationCalculator cut = new EducationCalculator();

    @ParameterizedTest
    @DisplayName("Should return point attached to enum element")
    @EnumSource(Education.class)
    public void test(Education education) {
        //given
        Person person = PersonTestFactory.create(education);
        //when
        int scoring = cut.calculate(person);
        //then
        assertEquals(education.getScoringPoints(), scoring);
    }
}