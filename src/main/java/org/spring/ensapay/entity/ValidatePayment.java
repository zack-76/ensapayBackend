package org.spring.ensapay.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class ValidatePayment {
    @Id
    private String username;
    @NotBlank(message = "token is required")
    private Integer token;
}
