package org.spring.ensapay.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ValidatePaymentDto {

    private Integer generatedToken;
    private Long clientId;
    private Integer impaye;
    private String codeCreditor;
    private String codeDept;

}
