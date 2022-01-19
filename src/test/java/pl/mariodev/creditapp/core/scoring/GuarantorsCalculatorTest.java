package pl.mariodev.creditapp.core.scoring;

import org.junit.jupiter.api.Test;
import org.mockito.internal.util.collections.Sets;
import pl.mariodev.creditapp.core.model.*;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GuarantorsCalculatorTest {
    private GuarantorsCalculator cut = new GuarantorsCalculator();

    @Test
    public void test1() {
        //given
        NaturalPerson person = createNaturalPerson();
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 50_000, 30);
        Set<Guarantor> guarantorSet = Set.of(new Guarantor("45464564564", 18));
        CreditApplication creditApplication = CreditApplicationTestFactory.create(person, purposeOfLoan, guarantorSet);
        //when
        int scoring = cut.calculate(creditApplication);
        //then
        assertEquals(50, scoring);
    }

    @Test
    public void test2() {
        //given
        NaturalPerson person = createNaturalPerson();
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 50_000, 30);
        Set<Guarantor> guarantorSet = Set.of(new Guarantor("45464564564", 18), new Guarantor("49464564564", 41));
        CreditApplication creditApplication = CreditApplicationTestFactory.create(person, purposeOfLoan, guarantorSet);
        //when
        int scoring = cut.calculate(creditApplication);
        //then
        assertEquals(75, scoring);
    }

    private NaturalPerson createNaturalPerson() {
        NaturalPerson person = NaturalPerson.Builder
                .create()
                .withFamilyMembers(new ArrayList<>())
                .withPersonalData(PersonalData.Builder.create()
                        .withName("Test")
                        .withLastName("Test")
                        .withMothersMaidenName("Test")
                        .withEducation(Education.MIDDLE)
                        .withMartialStatus(MaritalStatus.MARRIED)
                        .build())
                .withFinanceData(new FinanceData(new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, 10_000)))
                .build();
        return person;
    }

}