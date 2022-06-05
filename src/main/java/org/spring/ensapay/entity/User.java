package org.spring.ensapay.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class User {

    //Attributes
    @Id
    private String username;   // signin using phoneNumber for clients
    private String userPassword;
    private String roleName;

    @OneToOne(mappedBy = "agentUser",cascade = CascadeType.ALL)
    private Agent agent;

    @OneToOne(mappedBy = "clientUser",cascade = CascadeType.ALL)
    private Client client;

    @OneToOne(mappedBy = "backofficeUser",cascade = CascadeType.ALL)
    private Backoffice backoffice;

}
