package org.spring.ensapay.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Entity
@Getter
@Setter
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long agentId;
    //@Column(nullable = false,length = 25)
    private String agentPhone;
    //@Column(nullable = false,length = 20)
    private String agentFirstName;
    //@Column(nullable = false,length = 20)
    private String agentLastName;
    //@Column(nullable = false)
    private String agentAddress;
    //@Column(nullable = false,length = 20)
    private String agentBirthDate;
    //@Column(nullable = false,length = 20,name="agent_cin")
    private String agentCIN;
    //@Column(nullable = false,length = 100)
    private String agentEmail;

    private boolean firstConnection;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    User agentUser;
}
