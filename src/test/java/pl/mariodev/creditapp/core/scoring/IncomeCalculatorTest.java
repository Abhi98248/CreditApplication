package pl.mariodev.creditapp.core.scoring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.BDDMockito;
import pl.mariodev.creditapp.core.CreditApplicationDecision;
import pl.mariodev.creditapp.core.DecisionType;
import pl.mariodev.creditapp.core.model.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;

class IncomeCalculatorTest {
    IncomeCalculator cut = new IncomeCalculator();

    @Test
    @DisplayName("should return 100 point for each 1000 zł form family member")
    public void test() {
        //given
        SourceOfIncome s1 = new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, 5000.00);
        Person person = PersonTestFactory.create(2, s1);
        //when
        int scoring = cut.calculate(person);
        //then
        assertEquals(200, scoring);

    }

    @Test
    @DisplayName("should return 100 point for each 1000 zł where there is more than 1 source")
    public void test2() {
        //given
        SourceOfIncome s1 = new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, 4000.00);
        SourceOfIncome s2 = new SourceOfIncome(IncomeType.RETIREMENT, 1000.00);
        Person person = PersonTestFactory.create(2, s1,s2);
        //when
        int scoring = cut.calculate(person);
        //then
        assertEquals(300, scoring);

    }

}