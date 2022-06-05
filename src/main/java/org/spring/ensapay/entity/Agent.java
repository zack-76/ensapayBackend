package org.spring.ensapay.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long agentId;
    //@Column(nullable = false)
    private String agentPhone;
    //@Column(nullable = false)
    private String agentFirstName;
    //@Column(nullable = false)
    private String agentLastName;
    //@Column(nullable = false)
    private String agentAddress;
    //@Column(nullable = false)
    private String agentBirthDate;
    //@Column(nullable = false)
    private String agentCIN;
    //@Column(nullable = false)
    private String agentEmail;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    User agentUser;
}
