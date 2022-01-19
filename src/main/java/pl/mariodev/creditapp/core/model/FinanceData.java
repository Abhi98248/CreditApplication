package pl.mariodev.creditapp.core.model;

import java.util.*;

public class FinanceData {
    private final List<SourceOfIncome> sourceOfIncomes;
    private final Set<Expense> expenses;

    public FinanceData(SourceOfIncome... sourceOfIncomes) {
        this.sourceOfIncomes = Arrays.asList(sourceOfIncomes);
        this.expenses = new HashSet<>();
    }

    public FinanceData(Set<Expense> expenses, SourceOfIncome... sourceOfIncomes) {
        this.sourceOfIncomes = Arrays.asList(sourceOfIncomes);
        this.expenses = expenses;
    }

    private Map<ExpenseType, Set<Expense>> getExpenseMap() {
        Map<ExpenseType, Set<Expense>> result = new HashMap<>();
        for (Expense expense : expenses) {
            if (result.containsKey(expense.getType())) {
                result.get(expense.getType()).add(expense);
            } else {
                Set<Expense> set = new HashSet<>();
                set.add(expense);
                result.put(expense.getType(), set);
            }
        }

        return result;
    }

    public double getSumOfExpenses(ExpenseType type) {
        double sum = 0.0;
        Map<ExpenseType, Set<Expense>> expenseTypeSetMap = getExpenseMap();
        if (expenseTypeSetMap.isEmpty()) {
            return sum;
        }
        for (Expense expense : expenseTypeSetMap.get(type)) {
            sum += expense.getAmount();
        }
        return sum;
    }

    public Set<Expense> getExpenses() {
        return expenses;
    }

    public List<SourceOfIncome> getSourceOfIncomes() {
        return sourceOfIncomes;
    }
}
