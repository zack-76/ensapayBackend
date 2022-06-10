package org.spring.ensapay.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ValidatePaymentDto {

    @NotBlank(message = "Token is required")
    @Pattern(regexp = "^[0-9]+[0-9]$")
    @Size(min = 6,max = 6)
    private Integer generatedToken;
    @NotBlank(message = "Your Id is required")
    @Pattern(regexp = "^[0-9]+[0-9]$")
    private Long clientId;
    @NotBlank(message = "Your Facture impaye is required")
    @Pattern(regexp = "^[0-9]+[0-9]$")
    private Integer impaye;
    @NotBlank(message = "Code Credetor is required")
    @Pattern(regexp = "^[a-zA-Z1-9]+$",message = "should only have letter and numbers")
    private String codeCreditor;
    @NotBlank(message = "Code Debt is required")
    @Pattern(regexp = "^[1-9]+$",message = "should only have numbers")
    private String codeDept;

}
