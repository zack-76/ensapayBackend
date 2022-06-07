package org.spring.ensapay.controller;

import org.spring.ensapay.entity.Creditor;
import org.spring.ensapay.webservice.WebServiceCMI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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



}
