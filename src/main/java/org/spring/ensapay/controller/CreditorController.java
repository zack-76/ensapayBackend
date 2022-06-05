package org.spring.ensapay.controller;

import org.spring.ensapay.service.CreditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class CreditorController {

    @Autowired
    private CreditorService creditorService;

    @PostConstruct
    public void initCreditor(){
        creditorService.initCreditors();
    }
}
