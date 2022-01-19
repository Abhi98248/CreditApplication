package pl.mariodev.creditapp.core.model;

import pl.mariodev.creditapp.core.scoring.IncomeCalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonTestFactory {

    public static NaturalPerson create(int numberDependants, SourceOfIncome... sourceOfIncomes) {
        List<FamilyMember> familyMemberList = new ArrayList<>();
        for (int i = 0; i < numberDependants - 1; i++){
            familyMemberList.add(new FamilyMember("John", 18));
        }
        PersonalData personalData = PersonalData
                .Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
                .withEducation(Education.MIDDLE)
                .withMartialStatus(MaritalStatus.MARRIED)
                .build();
        return new NaturalPerson
                .Builder()
                .create()
                .withFamilyMembers(familyMemberList)
                .withContactData(null)
                .withFinanceData(new FinanceData(sourceOfIncomes))
                .withPersonalData(personalData)
                .build();
    }

    public static NaturalPerson create(MaritalStatus maritalStatus) {
        List<FamilyMember> familyMemberList = Arrays.asList(new FamilyMember("James", 25));
        PersonalData personalData = PersonalData
                .Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
                .withEducation(Education.MIDDLE)
                .withMartialStatus(maritalStatus)
                .build();
        return new NaturalPerson
                .Builder()
                .create()
                .withFamilyMembers(familyMemberList)
                .withContactData(null)
                .withFinanceData(null)
                .withPersonalData(personalData)
                .build();
    }

    public static NaturalPerson create(Education education) {
        List<FamilyMember> familyMemberList = Arrays.asList(new FamilyMember("John", 18),
                new FamilyMember("Jane", 20));
        PersonalData personalData = PersonalData
                .Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
                .withEducation(education)
                .withMartialStatus(MaritalStatus.SINGLE)
                .build();

        return new NaturalPerson
                .Builder()
                .create()
                .withFamilyMembers(familyMemberList)
                .withContactData(null)
                .withFinanceData(null)
                .withPersonalData(personalData)
                .build();
    }

    public static NaturalPerson create() {
        List<FamilyMember> familyMemberList = Arrays.asList(new FamilyMember("John", 18));
        PersonalData personalData = PersonalData
                .Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
                .withEducation(Education.MIDDLE)
                .withMartialStatus(MaritalStatus.SINGLE)
                .build();
        return new NaturalPerson
                .Builder()
                .create()
                .withFamilyMembers(familyMemberList)
                .withContactData(null)
                .withFinanceData(null)
                .withPersonalData(personalData)
                .build();
    }

    public static NaturalPerson create(double totalMonthlyIncomeInPln, int numOfDependants, Education education, MaritalStatus maritalStatus) {
        List<FamilyMember> familyMemberList = new ArrayList<>();
        for (int i = 0; i < numOfDependants; i++){
            familyMemberList.add(new FamilyMember("John", 18));
        }
        PersonalData personalData = PersonalData
                .Builder
                .create()
                .withName("test")
                .withLastName("test")
                .withMothersMaidenName("test")
                .withEducation(education)
                .withMartialStatus(maritalStatus)
                .build();
        return new NaturalPerson
                .Builder()
                .create()
                .withFamilyMembers(familyMemberList)
                .withContactData(null)
                .withFinanceData(new FinanceData(new SourceOfIncome(IncomeType.SELF_EMPLOYMENT, totalMonthlyIncomeInPln)))
                .withPersonalData(personalData)
                .build();
    }
}
