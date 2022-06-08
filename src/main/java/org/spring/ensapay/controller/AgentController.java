package org.spring.ensapay.controller;

import lombok.extern.slf4j.Slf4j;
import org.spring.ensapay.dto.AgentDto;
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
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

@Slf4j
@RestController
@RequestMapping("/agent")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initAgent(){agentService.initAgent();}


    @PostMapping("/regiterNewUserAgent")
    @PreAuthorize("hasRole('Backoffice')")
    public ResponseEntity<String> regiterNewUserAgent(@RequestParam("identity") MultipartFile[] identities,
                                    @RequestParam("agentPhone") String agentPhone,
                                    @RequestParam("agentFirstName") String agentFirstName,
                                    @RequestParam("agentLastName") String agentLastName,
                                    @RequestParam("agentAddress") String agentAddress,
                                    @RequestParam("agentBirthDate")  String agentBirthDate,
                                    @RequestParam("agentCIN") String agentCIN,
                                    @RequestParam("agentEmail") String agentEmail)
            throws MessagingException, UnsupportedEncodingException {

        @Valid AgentDto agentDto = new AgentDto(agentPhone,agentFirstName,agentLastName,agentAddress,agentBirthDate,agentCIN,agentEmail);
        try {
            Arrays.asList(identities).stream().forEach(file -> {
                agentService.save(file);
            });

        } catch (Exception e) {
            log.warn("could't not store identites",e);
        }
        log.info("Agent"+agentDto.getAgentFirstName()+" "+agentDto.getAgentLastName() +"successfully added");
        return ResponseEntity.status(HttpStatus.OK).body(agentService.registerNewUserAgent(agentDto));
    }


    @GetMapping("/forAgent")
    @PreAuthorize("hasRole('Agent')")
    public String forBackoffice(){
        return "just Agent";
    }

}
