package org.spring.ensapay.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Debt {

    @Id
    private String codeDebt;
    //@NotBlank(message = "Name is required")
    private String nameDebt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "creditor_code",
            referencedColumnName = "codeCreditor"
    )
    private Creditor creditor;

}
