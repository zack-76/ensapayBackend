package org.spring.ensapay.controller;

import lombok.extern.slf4j.Slf4j;
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

    @GetMapping("/Creditors")
    public ResponseEntity<List<Creditor>> getCreditors(){
        return ResponseEntity.status(200).body(webServiceCMI.getAllCreditor());
    }

    @PostMapping("/getImpay")
    @PreAuthorize("hasRole('Client')")
    public ResponseEntity<Integer> getImpay(@RequestParam("reference") String ref){
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
    public ResponseEntity<String> validatePayment(@RequestParam("generatedToken") Integer generatedToken,
                                                  @RequestParam("clientId")Long clientId,
                                                  @RequestParam("impaye")Integer impaye,
                                                  @RequestParam("codeCreditor")String codeCreditor,
                                                  @RequestParam("codeDept")String codeDept)
            throws MessagingException, UnsupportedEncodingException {
            log.info("Payment validated");
            return ResponseEntity.status(200).body(webServiceCMI.validate(generatedToken,clientId,impaye,codeCreditor,codeDept));
    }

    @GetMapping("/factures")
    public List<Facture> getAllFactures(){
        return webServiceCMI.getFactures();
    }
}
