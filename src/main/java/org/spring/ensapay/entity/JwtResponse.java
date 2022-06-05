package org.spring.ensapay.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

    private User user;
    private String jwtToken;

}