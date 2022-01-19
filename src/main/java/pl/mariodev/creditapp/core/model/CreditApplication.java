package pl.mariodev.creditapp.core.model;

import pl.mariodev.creditapp.core.annotation.NotNull;

import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

public class CreditApplication {
    @NotNull
    private final Person person;
    @NotNull
    private final UUID id;
    @NotNull
    private final PurposeOfLoan purposeOfLoan;
    @NotNull
    private final Set<Guarantor> guarantors;

    public CreditApplication(Person person, PurposeOfLoan purposeOfLoan) {
        this.person = person;
        this.id = UUID.randomUUID();
        this.purposeOfLoan = purposeOfLoan;
        this.guarantors = new TreeSet<>();
    }

    public CreditApplication(Person person, PurposeOfLoan purposeOfLoan, Set<Guarantor> guarantors) {
        this.person = person;
        this.id = UUID.randomUUID();
        this.purposeOfLoan = purposeOfLoan;
        this.guarantors = new TreeSet<>(guarantors);
    }

    public Set<Guarantor> getGuarantors() {
        return guarantors;
    }

    public UUID getId() {
        return id;
    }

    public PurposeOfLoan getPurposeOfLoan() {
        return purposeOfLoan;
    }

    public Person getPerson() {
        return person;
    }
}
