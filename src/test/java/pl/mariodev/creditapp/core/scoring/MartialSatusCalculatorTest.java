package pl.mariodev.creditapp.core.scoring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import pl.mariodev.creditapp.core.model.MaritalStatus;
import pl.mariodev.creditapp.core.model.Person;
import pl.mariodev.creditapp.core.model.PersonTestFactory;

import static org.junit.jupiter.api.Assertions.*;

class MartialSatusCalculatorTest {
    private MartialStatusCalculator cut = new MartialStatusCalculator();

    @ParameterizedTest
    @DisplayName("Should return point attached to enum element")
    @EnumSource(MaritalStatus.class)
    public void test(MaritalStatus maritalStatus) {
        //given
        Person person = PersonTestFactory.create(maritalStatus);
        //when
        int scoring = cut.calculate(person);
        //then
        assertEquals(maritalStatus.getScoringPoints(), scoring);
    }
}