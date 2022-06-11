package org.spring.ensapay.controller;

import org.spring.ensapay.service.BackofficeService;
import org.spring.ensapay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/backoffice")
public class BackofficeController {

    @Autowired
    private BackofficeService backofficeService;

    @Autowired
    private UserService userService;

//    @PostConstruct
//    public void initRoleAndUser() {
//        backofficeService.initBackoffice();
//    }



    @GetMapping("/forBackoffice")
    @PreAuthorize("hasRole('Backoffice')")
    public String forBackoffice(){
        return "just BackOffice";
    }
}
