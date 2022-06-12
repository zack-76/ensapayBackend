package org.spring.ensapay.controller;

import org.spring.ensapay.entity.Agent;
import org.spring.ensapay.entity.Backoffice;
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

      @PostConstruct
     public void initRoleAndUser() {
          backofficeService.initBackoffice();
      }

    @GetMapping("/profileBackoffice/{username}")
    public @ResponseBody
    Backoffice getBackoffice(@PathVariable(value  = "username") String username) {
        return this.backofficeService.getBackofficeProfile(username);
    }


    @GetMapping("/forBackoffice")
    @PreAuthorize("hasRole('Backoffice')")
    public String forBackoffice(){
        return "just BackOffice";
    }
}
