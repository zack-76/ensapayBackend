package org.spring.ensapay.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;


@Entity
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;
    //@Column(nullable = false,length = 25)
    private String clientFirstName;
    //@Column(nullable = false,length = 25)
    private String clientLastName;
    //@Column(nullable = false,length = 25)
    private String clientPhone;
    //@Column(nullable = false)
    private String clientAddress;
    //@Column(nullable = false,length = 20)
    private String clientBirthDate;
    //@Column(nullable = false,length = 10,name="client_cin")
    private String clientCIN;
    //@Column(nullable = false)
    private Integer clientSolde;
   // @Column(nullable = false,length=100)
    private String clientEmail;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    User clientUser;
}
