package org.spring.ensapay.controller;

import lombok.extern.slf4j.Slf4j;
import org.spring.ensapay.dto.ValidatePaymentDto;
import org.spring.ensapay.entity.Facture;
import org.spring.ensapay.entity.ValidatePayment;
import org.spring.ensapay.webservice.WebServiceCMI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/CMIservice")
public class WebServiceController {

    @Autowired
    private WebServiceCMI webServiceCMI;


    @PostMapping("/getImpay/{username}/{reference}")
    @PreAuthorize("hasRole('Client')")
    public ResponseEntity<Integer> getImpay(@PathVariable("reference") String reference,@PathVariable("username") String username){
        log.info("Impay pased to: "+reference);
        return ResponseEntity.ok().body(webServiceCMI.getImpay(reference,username));
    }

    @GetMapping("/getValidateToken/{username}")
    //@PreAuthorize("hasRole('Client')")
    public ResponseEntity<String> getValidateSms(@PathVariable("username") String username){
        try{
            webServiceCMI.sendValidateSms(username);
            log.info("Validate token pased in SMS to: "+username);
            return ResponseEntity.ok().body("We have sent you an SMS to identify you! ");
        }catch (Exception e){
            return ResponseEntity.status(401).body("Something went wrowng");
        }
    }

    @PostMapping("/validateToken")
    @PreAuthorize("hasRole('Client')")
    public ResponseEntity<Boolean> ValidateToken(@RequestBody ValidatePayment validatePayment){
        log.info("Validate Token passed to the Client "+validatePayment.getUsername());
        return  ResponseEntity.ok().body(webServiceCMI.validateToken(validatePayment));
    }


    @PostMapping("/validatePayment/{username}")
    @PreAuthorize("hasRole('Client')")
    public ResponseEntity<String> validatePayment(@RequestBody ValidatePaymentDto validatePaymentDto,@PathVariable("username") String username)
            throws MessagingException, UnsupportedEncodingException {
            log.info("Payment validated");
            return ResponseEntity.status(200).body(webServiceCMI.validate(validatePaymentDto,username));
    }

    @GetMapping("/factures/{clientName}")
    public ResponseEntity<List<Facture>> getAllFactures(@PathVariable("clientName") String clientName){
        return ResponseEntity.status(200).body(webServiceCMI.getFacutreByClientName(clientName));
    }
}
