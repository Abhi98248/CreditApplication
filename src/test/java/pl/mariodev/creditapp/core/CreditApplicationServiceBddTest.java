package pl.mariodev.creditapp.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.mariodev.creditapp.core.exception.RequirementNotMetCause;
import pl.mariodev.creditapp.core.model.*;
import pl.mariodev.creditapp.core.scoring.EducationCalculator;
import pl.mariodev.creditapp.core.scoring.GuarantorsCalculator;
import pl.mariodev.creditapp.core.scoring.IncomeCalculator;
import pl.mariodev.creditapp.core.scoring.MartialStatusCalculator;
import pl.mariodev.creditapp.core.validation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static pl.mariodev.creditapp.core.model.ExpenseType.PERSONAL;

@ExtendWith(MockitoExtension.class)
class CreditApplicationServiceBddTest {
    private EducationCalculator educationCalculator = new EducationCalculator();
    private IncomeCalculator incomeCalculator = new IncomeCalculator();
    private MartialStatusCalculator martialStatusCalculator = new MartialStatusCalculator();
    private SelfEmployedScoringCalculator selfEmployedScoringCalculator = new SelfEmployedScoringCalculator();
    private GuarantorsCalculator guarantorsCalculator = new GuarantorsCalculator();
    private PersonScoringCalculatorFactory personScoringCalculatorFactory = new PersonScoringCalculatorFactory(selfEmployedScoringCalculator, educationCalculator, incomeCalculator, martialStatusCalculator, guarantorsCalculator);
    private GuarantorValidator guarantorValidator = new GuarantorValidator();
    //    private CreditApplicationService service = new CreditApplicationService(personScoringCalculatorFactory, new CreditRatingCalculator(), creditApplicationValidator);
    private CreditApplicationValidator creditApplicationValidator = new CreditApplicationValidator(new PersonValidator(new PersonalDataValidator()), new PurposeOfLoanValidator(), guarantorValidator);
    private CompoundPostValidator compoundPostValidator = new CompoundPostValidator(new PurposeOfLoanPostValidator(), new ExpansePostValidator());
    private CreditApplicationService cut = new CreditApplicationService(personScoringCalculatorFactory, new CreditRatingCalculator(), creditApplicationValidator, compoundPostValidator);


    @Test
    @DisplayName("should return decision is NEGATIVE_REQUIREMENTS_NOT_MET, min loan amount requirement is not met")
    public void test1() {
        //given
        List<FamilyMember> familyMemberList = Arrays.asList(new FamilyMember("John", 18));
        NaturalPerson person = NaturalPerson.Builder
                .create()
                .withFamilyMembers(familyMemberList)
                .withPersonalData(PersonalData.Builder.create()
                        .withName("Test")
                        .withLastName("Test")
                        .withMothersMaidenName("Test")
                        .withEducation(Education.MIDDLE)
                        .withMartialStatus(MaritalStatus.MARRIED)
                        .build())
                .withFinanceData(new FinanceData(new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, 10_000)))
                .build();
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 50_000, 30);
        CreditApplication creditApplication = CreditApplicationTestFactory.create(person, purposeOfLoan);
        //when
        CreditApplicationDecision decision = cut.getDecision(creditApplication);
        //then
        assertEquals(DecisionType.NEGATIVE_REQUIREMENTS_NOT_MET, decision.getType());
        assertEquals(600, decision.getScoring());
        assertEquals(360_000, decision.getCreditRate());

    }

    @Test
    @DisplayName("should return decision is NEGATIVE, when years since founded <2")
    public void test2() {
        //given
        List<FamilyMember> familyMemberList = Arrays.asList(new FamilyMember("John", 18));
        SelfEmployed person = SelfEmployed.Builder
                .create()
                .withFamilyMembers(familyMemberList)
                .withPersonalData(PersonalData.Builder.create()
                        .withName("Test")
                        .withLastName("Test")
                        .withMothersMaidenName("Test")
                        .withEducation(Education.MIDDLE)
                        .withMartialStatus(MaritalStatus.MARRIED)
                        .build())
                .withFinanceData(new FinanceData(new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, 7_000)))
                .withYearsSinceFounded(1)
                .build();
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 500_000, 30);
        CreditApplication creditApplication = CreditApplicationTestFactory.create(person, purposeOfLoan);
        //when
        CreditApplicationDecision decision = cut.getDecision(creditApplication);
        //then
        assertEquals(DecisionType.NEGATIVE_SCORING, decision.getType());
        assertEquals(200, decision.getScoring());
    }

    @Test
    @DisplayName("should return decision is POSITIVE, when years since founded >=2")
    public void test3() {
        //given
        List<FamilyMember> familyMemberList = Arrays.asList(new FamilyMember("John", 18));
        SelfEmployed person = SelfEmployed.Builder
                .create()
                .withFamilyMembers(familyMemberList)
                .withPersonalData(PersonalData.Builder.create()
                        .withName("Test")
                        .withLastName("Test")
                        .withMothersMaidenName("Test")
                        .withEducation(Education.MIDDLE)
                        .withMartialStatus(MaritalStatus.MARRIED)
                        .build())
                .withFinanceData(new FinanceData(new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, 7_000)))
                .withYearsSinceFounded(3)
                .build();
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 500_000, 30);
        CreditApplication creditApplication = CreditApplicationTestFactory.create(person, purposeOfLoan);
        //when
        CreditApplicationDecision decision = cut.getDecision(creditApplication);
        //then
        assertEquals(DecisionType.CONTACT_REQUIRED, decision.getType());
        assertEquals(400, decision.getScoring());
    }

    @Test
    @DisplayName("should return Decision is negative requirements not met, cause too high personal expense")
    public void test4() {
        //given
Set<Expense> expenses = Set.of(new Expense("1", PERSONAL, 500),
        new Expense("2", PERSONAL,750));
        FinanceData financeData = new FinanceData(expenses, new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, 2_000));
        SelfEmployed person = SelfEmployed.Builder
                .create()
                .withPersonalData(PersonalData.Builder.create()
                        .withName("Test")
                        .withLastName("Test")
                        .withMothersMaidenName("Test")
                        .withEducation(Education.MIDDLE)
                        .withMartialStatus(MaritalStatus.MARRIED)
                        .build())
                .withFinanceData(financeData)
                .withYearsSinceFounded(3)
                .build();
        PurposeOfLoan purposeOfLoan = new PurposeOfLoan(PurposeOfLoanType.MORTGAGE, 500_000, 30);
        CreditApplication creditApplication = CreditApplicationTestFactory.create(person, purposeOfLoan);
        //when
        CreditApplicationDecision decision = cut.getDecision(creditApplication);
        //then
        assertEquals(DecisionType.NEGATIVE_REQUIREMENTS_NOT_MET, decision.getType());
        assertTrue(decision.getRequirementNotMetCause().isPresent());
        assertEquals(RequirementNotMetCause.TOO_HIGH_EXPENSES, decision.getRequirementNotMetCause().get());
    }

}