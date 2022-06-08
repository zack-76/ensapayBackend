package org.spring.ensapay.controller;

import lombok.extern.slf4j.Slf4j;
import org.spring.ensapay.dto.ValidatePaymentDto;
import org.spring.ensapay.entity.Creditor;
import org.spring.ensapay.entity.Facture;
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


    @PostMapping("/getImpay")
    @PreAuthorize("hasRole('Client')")
    public ResponseEntity<Integer> getImpay(@RequestBody String ref){
        log.info("Impay pased to: "+ref);
        return ResponseEntity.ok().body(webServiceCMI.getImpay(ref));
    }

    @GetMapping("/validateToken")
    @PreAuthorize("hasRole('Client')")
    public ResponseEntity<Integer> getValidateToken(){
        log.info("Validate Token passed to the Client ");
        return  ResponseEntity.ok().body(webServiceCMI.sendGeneratedOTP());
    }

    @PostMapping("/validatePayment")
    @PreAuthorize("hasRole('Client')")
    public ResponseEntity<String> validatePayment(@RequestBody ValidatePaymentDto validatePaymentDto)
            throws MessagingException, UnsupportedEncodingException {
            log.info("Payment validated");
            return ResponseEntity.status(200).body(webServiceCMI.validate(validatePaymentDto));
    }

    @GetMapping("/factures/{clientName}")
    public ResponseEntity<List<Facture>> getAllFactures(@PathVariable("clientName") String clientName){
        return ResponseEntity.status(200).body(webServiceCMI.getFacutreByClientName(clientName));
    }
}
