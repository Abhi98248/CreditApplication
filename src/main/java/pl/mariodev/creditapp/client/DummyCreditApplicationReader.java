package pl.mariodev.creditapp.client;

import pl.mariodev.creditapp.core.model.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class DummyCreditApplicationReader implements CreditApplicationReader{
    @Override
    public CreditApplication read() {
        FamilyMember john = new FamilyMember("John", 18);
        FamilyMember jane = new FamilyMember("Beatrice", 40);
        FamilyMember susie = new FamilyMember("Susie", 5);
        List<FamilyMember> familyMemberList = Arrays.asList(john,
                jane,
                susie);

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
        Set<Guarantor> guarantors = Set.of(new Guarantor("12341234123", 18),new Guarantor("22341234123", 41));
        CreditApplication creditApplication = new CreditApplication(person, purposeOfLoan, guarantors);

        return creditApplication;
    }
}
