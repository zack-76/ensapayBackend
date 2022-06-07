package org.spring.ensapay.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AgentDto {

    @NotBlank(message = "Phone is required")
    @Size(min = 10,max = 25)
    @Pattern(regexp = "^[0-9]+[0-9]$")
    private String agentPhone;
    @NotBlank(message = "First name is required")
    @Pattern(regexp = "^[a-zA-Z]+$",message = "should only have letter")
    private String agentFirstName;
    @NotBlank(message = "Last Name is required")
    @Pattern(regexp = "^[a-zA-Z]+$",message = "should only have letter")
    private String agentLastName;
    @NotBlank(message = "Adresse is required")
    private String agentAddress;
    @NotBlank(message = "BirthDate is required")
    private String agentBirthDate;
    @NotBlank(message = "Identity is required")
    private String agentCIN;
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be Valid")
    private String agentEmail;
}
