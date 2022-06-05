package org.spring.ensapay.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class Backoffice {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long backofficeId;
    //@Column(nullable = false)
    private String backofficePhone;
    //@Column(nullable = false)
    private String backofficeFirstName;
    //@Column(nullable = false)
    private String backofficeLastName;
    //@Column(nullable = false)
    private String backofficeAddress;
   // @Column(nullable = false)
    private String backofficeBirthDate;
    //@Column(nullable = false)
    private String backofficeCIN;
    //@Column(nullable = false)
    private String backofficeEmail;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    User backofficeUser;
}
