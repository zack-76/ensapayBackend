package org.spring.ensapay.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Creditor {

    @Id
    private String codeCreditor;
    @NotBlank(message = "name is required")
    private String nameCreditor;
    @NotBlank(message = "Category is required")
    private String categorieCreditor;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(
            name="creditor_code",
            referencedColumnName = "codeCreditor"
    )
    private Set<Debt> debts;

    }
