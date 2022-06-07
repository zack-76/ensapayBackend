package org.spring.ensapay.controller;

import org.spring.ensapay.entity.Creditor;
import org.spring.ensapay.webservice.WebServiceCMI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

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
    public ResponseEntity<Integer> getImpay(@RequestParam("reference") String ref){
        return ResponseEntity.ok().body(webServiceCMI.getImpay(ref));
    }

    @GetMapping("/validateToken")
    public ResponseEntity<Integer> getValidateToken(){
        return  ResponseEntity.ok().body(webServiceCMI.sendGeneratedOTP());
    }

    @PostMapping("/validatePayment")
    public ResponseEntity<String> validatePayment(@RequestParam("generatedToken") Integer generatedToken,
                                                  @RequestParam("clientId")Long clientId,
                                                  @RequestParam("impaye")Integer impaye,
                                                  @RequestParam("codeCreditor")String codeCreditor,
                                                  @RequestParam("codeDept")String codeDept)
            throws MessagingException, UnsupportedEncodingException {
        return ResponseEntity.status(200).body(webServiceCMI.validate(generatedToken,clientId,impaye,codeCreditor,codeDept));
    }


}
