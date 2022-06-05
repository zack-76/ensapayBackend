package org.spring.ensapay.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;
    //@Column(nullable = false)
    private String clientFirstName;
    //@Column(nullable = false)
    private String clientLastName;
    //@Column(nullable = false)
    private String clientPhone;
    //@Column(nullable = false)
    private String clientAddress;
    //@Column(nullable = false)
    private String clientBirthDate;
    //@Column(nullable = false)
    private String clientCIN;
    //@Column(nullable = false)
    private Integer clientSolde;
   // @Column(nullable = false)
    private String clientEmail;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    User clientUser;
}
