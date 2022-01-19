package pl.mariodev.creditapp.client;

import pl.mariodev.creditapp.core.model.*;

public class EnumValidator {

    public static boolean validateMaritalStatus(String martialStatusString) {
        for(MaritalStatus maritalStatus : MaritalStatus.values()){
            if(maritalStatus.name().equals(martialStatusString)){
                return true;
            }
        }
        return false;
    }

    public static boolean validateEducation(String educationStr){
        for(Education education : Education.values()){
            if(education.name().equals(educationStr)){
                return true;
            }
        }
        return false;
    }

    public static boolean validateIncomeType(String incomeTypeStr){
        for(IncomeType incomeType : IncomeType.values()){
            if(incomeType.name().equals(incomeTypeStr)){
                return true;
            }
        }
        return false;
    }

    public static boolean validatePurposeOfLoanYpe(String purposeOfLoanTypeStr){
        for(PurposeOfLoanType purposeOfLoanType: PurposeOfLoanType.values()){
            if(purposeOfLoanType.name().equals(purposeOfLoanTypeStr)){
                return true;
            }
        }
        return false;
    }

}
