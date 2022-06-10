package org.spring.ensapay.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    @NotBlank(message = "First name is required")
    @Pattern(regexp = "^[a-zA-Z]+$",message = "should only have letter")
    private String clientFirstName;
    @NotBlank(message = "Last Name is required")
    @Pattern(regexp = "^[a-zA-Z]+$",message = "should only have letter")
    private String clientLastName;
    @NotBlank(message = "Phone is required")
    @Size(min = 10,max = 25)
    @Pattern(regexp = "^[0-9]+[0-9]$")
    private String clientPhone;
    @NotBlank(message = "Address is required")
    private String clientAddress;
   //@NotBlank(message = "BirthDate is required")
    //private String clientBirthDate;
   // @NotBlank(message = "Identity is required")
    //private String clientCIN;
    @NotBlank(message = "Entry Solde is required")
    @PositiveOrZero(message = "Solde should greater or equals to 0")
    private Integer clientSolde;
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be Valid")
    private String clientEmail;
    private String ClientUsername;


    private String ClientCity;
    private String ClientZip;
    private String ClientCountry;

}
