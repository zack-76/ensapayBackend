package org.spring.ensapay.controller;

import org.spring.ensapay.entity.Creditor;
import org.spring.ensapay.service.CreditorService;
import org.spring.ensapay.webservice.WebServiceCMI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/creditor")
public class CreditorController {

    @Autowired
    private CreditorService creditorService;

    @Autowired
    private WebServiceCMI webServiceCMI;

    @PostConstruct
    public void initCreditor(){
        creditorService.initCreditors();
    }

    @GetMapping("/getAll")
    public List<Creditor> getAllCreditorsList(){
        return webServiceCMI.getAllCreditor();
    }

}
