package org.spring.ensapay.controller;

import lombok.extern.slf4j.Slf4j;
import org.spring.ensapay.entity.Agent;
import org.spring.ensapay.entity.JwtRequest;
import org.spring.ensapay.entity.JwtResponse;
import org.spring.ensapay.service.AgentService;
import org.spring.ensapay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
public class AgentController {

    @Autowired
    private AgentService agentService;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initAgent(){agentService.initAgent();}

    @PostMapping("/regiterNewUserAgent")
    @PreAuthorize("hasRole('BackOffice')")
    public ResponseEntity<String> regiterNewUserAgent(@RequestBody Agent agent) throws MessagingException,
            UnsupportedEncodingException {

        return ResponseEntity.status(HttpStatus.OK).body(agentService.registerNewUserAgent(agent));
    }


    @PostMapping("/uploadAgentIdentities")
    @PreAuthorize("hasRole('Backoffice')")
    public void uploadAgentIdentity(@RequestParam("identity") MultipartFile[] identities) {
        try {
            List<String> fileNames = new ArrayList<>();
            Arrays.asList(identities).stream().forEach(file -> {
                agentService.save(file);
                fileNames.add(file.getOriginalFilename());
            });
        } catch (Exception e) {
            log.warn("could't not store identites",e);
        }
    }


    @GetMapping("/forAgent")
    @PreAuthorize("hasRole('Agent')")
    public String forBackoffice(){
        return "just Agent";
    }
}
