package org.spring.ensapay.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {

    private String username; //phone for client and username for backoffice and agent
    private String userPassword;

}