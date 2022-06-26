package org.spring.ensapay.controller;

import org.spring.ensapay.entity.Agent;
import org.spring.ensapay.entity.Backoffice;

import org.spring.ensapay.service.BackofficeService;
import org.spring.ensapay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/backoffice")
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
    public String forBackoffice() {
        return "just BackOffice";
    }
    @GetMapping("/profileBackOffice/{username}")
    public @ResponseBody
    Backoffice getBackOffice(@PathVariable(value  = "username") String username) {
        return this.backofficeService.getBacckOfficeProfile(username);
    }
    @GetMapping("/getAgents/{id}")
    @PreAuthorize("hasRole('Backoffice')")
    public ResponseEntity<List<Agent>> getAllAgents(
                                                        @PathVariable("id") Long id){
        return ResponseEntity.status(200).body(this.backofficeService.getAgents(id));
    }
    @GetMapping("/getAgents/{id}/{agent}")
    @PreAuthorize("hasRole('Backoffice')")
    public ResponseEntity<List<Agent>> getAllAgenttwithSearch(
            @PathVariable("id") Long id,@PathVariable("agent") String name){
        return ResponseEntity.status(200).body(this.backofficeService.getAgents(id,name));
    }
}
