package org.spring.ensapay.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Facture {


    @Id
    private Integer reference ;
    @Column(nullable = false)
    private String clientName;
    @Column(nullable = false)
    private String creditorName;
    @Column(nullable = false)
    private String DebtName;
    @Column(nullable = false)
    private Integer impaye;

}
