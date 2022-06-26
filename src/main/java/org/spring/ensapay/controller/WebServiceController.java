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
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/CMIservice")
public class WebServiceController {


    @Autowired
    private WebServiceCMI webServiceCMI;


    @PostMapping("/getImpay/{username}/{reference}")
  //@PreAuthorize("hasRole('Client')")
    public @ResponseBody
    Map<String,String> getImpay(@PathVariable("reference") String reference, @PathVariable("username") String username){
        log.info("Impay pased to: "+reference);
        return webServiceCMI.getImpay(reference,username);
    }

    @GetMapping("/validateToken/{username}")
    @PreAuthorize("hasRole('Client')")
    public ResponseEntity<String> getValidateToken(@PathVariable("username") String username){
        log.info("Validate Token passed to the Client "+username);
        return  ResponseEntity.ok().body(webServiceCMI.sendValidatetoken(username));
    }

    @PostMapping("/validatePayment/{username}")
    //@PreAuthorize("hasRole('Client')")
    public ResponseEntity<String> validatePayment(@RequestBody ValidatePaymentDto validatePaymentDto,@PathVariable("username") String username) {
        try {
            log.info("Payment validated");
            return ResponseEntity.status(200).body(webServiceCMI.validate(validatePaymentDto, username));
        } catch (Exception e) {
            return ResponseEntity.status(400).body("error");
        }
    }
    @GetMapping("/getValidateToken/{username}")
    //@PreAuthorize("hasRole('Client')")
    public ResponseEntity<String> getValidateSms(@PathVariable("username") String username){
        try{
            webServiceCMI.sendValidateSms(username);
            log.info("Validate token pased in SMS to: "+username);
            return ResponseEntity.ok().body("We have sent you an SMS to identify you! ");
        }catch (Exception e){
            return ResponseEntity.status(401).body("Something went wrong");
        }
    }

    @PostMapping("/validateToken/{token}/{username}")
    public ResponseEntity<String> validateToken(@PathVariable("username") String username,@PathVariable("token") String token){
           try{
               System.out.println(token);
               System.out.println(username);
               webServiceCMI.validateToken(username,token);
               return ResponseEntity.ok().body("next");

           }catch (Exception e){
               return ResponseEntity.status(400).body("echec");
           }


    }

    @GetMapping("/factures/{username}")
    public ResponseEntity<List<Facture>> getAllFactures(@PathVariable("username") String username){
        return ResponseEntity.status(200).body(webServiceCMI.getFacutreByClientName(username));
    }

    @GetMapping("/factures/{username}/{creditor}")
    public ResponseEntity<List<Facture>> getsearchFactures(@PathVariable("username") String username,@PathVariable("creditor") String creditor){
        return ResponseEntity.status(200).body(webServiceCMI.getFacutreByClientNameCreditor(username,creditor));
    }
}
