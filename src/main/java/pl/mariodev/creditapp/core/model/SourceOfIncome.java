package pl.mariodev.creditapp.core.model;

public class SourceOfIncome {
    private final IncomeType incomeType;
    private final double getMonthlyIncome;

    public SourceOfIncome(IncomeType incomeType, double getMonthlyIncome) {
        this.incomeType = incomeType;
        this.getMonthlyIncome = getMonthlyIncome;
    }

    public IncomeType getIncomeType() {
        return incomeType;
    }

    public double getGetMonthlyIncome() {
        return getMonthlyIncome;
    }
}
