package org.spring.ensapay.entity;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {

    @NotBlank(message = "Username is required")
    private String username; //phone for client and username for backoffice and agent
    @NotBlank(message = "Password is required")
    @Size(min = 8,message = "Password sould at least 8 characters")
    private String userPassword;

}