package org.spring.ensapay.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    }
