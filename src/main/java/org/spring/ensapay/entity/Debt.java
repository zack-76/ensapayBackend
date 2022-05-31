package org.spring.ensapay.entity;

import javax.persistence.*;

@Entity
public class Debt {

    @Id
    private String codeDebt;
    private String nameDebt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "creditor_code",
            referencedColumnName = "codeCreditor"
    )
    private Creditor creditor;

    public String getCodeDebt() {
        return codeDebt;
    }

    public void setCodeDebt(String codeDebt) {
        this.codeDebt = codeDebt;
    }

    public String getNameDebt() {
        return nameDebt;
    }

    public void setNameDebt(String nameDebt) {
        this.nameDebt = nameDebt;
    }

    public Creditor getCreditor() {
        return creditor;
    }

    public void setCreditor(Creditor creditor) {
        this.creditor = creditor;
    }
}
