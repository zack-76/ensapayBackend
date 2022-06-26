package org.spring.ensapay.entity;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Getter
@Setter
public class User {

    //Attributes
    @Id
    private String username;   // signin using phoneNumber for clients
    //@Column(nullable = false)
    //@NotBlank(message = "Role is required ")
    //@Size(min = 8, message = "The Password Should be greater then 8 characters")
    private String userPassword;
    //@Column(nullable = false)
    //@NotBlank(message = "Role is required ")
    private String roleName;


}
