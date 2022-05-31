package org.spring.ensapay.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Creditor {

    @Id
    private String codeCreditor;
    private String nameCreditor;
    private String categorieCreditor;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(
            name="creditor_code",
            referencedColumnName = "codeCreditor"
    )
    private Set<Debt> debts;

    public String getCodeCreditor() {
        return codeCreditor;
    }

    public void setCodeCreditor(String codeCreditor) {
        this.codeCreditor = codeCreditor;
    }

    public String getNameCredior() {
        return nameCreditor;
    }

    public void setNameCredior(String nameCreditor) {
        this.nameCreditor = nameCreditor;
    }

    public String getCategorieCreditor() {
        return categorieCreditor;
    }

    public void setCategorieCreditor(String categorieCreditor) {
        this.categorieCreditor = categorieCreditor;
    }

    public Set<Debt> getDebts() {
        return debts;
    }

    public void setDebts(Set<Debt> debts) {
        this.debts = debts;
    }
}
