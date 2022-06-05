package org.spring.ensapay.controller;

import org.spring.ensapay.entity.JwtRequest;
import org.spring.ensapay.entity.JwtResponse;
import org.spring.ensapay.service.BackofficeService;
import org.spring.ensapay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class BackofficeController {

    @Autowired
    private BackofficeService backofficeService;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        backofficeService.initBackoffice();
    }



    @GetMapping("/forBackoffice")
    @PreAuthorize("hasRole('Backoffice')")
    public String forBackoffice(){
        return "just BackOffice";
    }
}
