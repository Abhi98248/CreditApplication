package pl.mariodev.creditapp.core.model;

import java.util.Objects;

public class Expense {
    private final String name;
    private final ExpenseType type;
    private final double acount;

    public Expense(String name, ExpenseType type, double acount) {
        this.name = name;
        this.type = type;
        this.acount = acount;
    }

    public String getName() {
        return name;
    }

    public ExpenseType getType() {
        return type;
    }

    public double getAmount() {
        return acount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return name.equals(expense.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
