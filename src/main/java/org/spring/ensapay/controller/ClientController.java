package org.spring.ensapay.controller;

import lombok.extern.slf4j.Slf4j;
import org.spring.ensapay.dto.ClientDto;
import org.spring.ensapay.entity.Client;
import org.spring.ensapay.service.ClientService;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initAgent(){clientService.initClient();}


    @PostMapping("/regiterNewUserClient")
    //@PreAuthorize("hasRole('Agent')")
    public ResponseEntity<String> regiterNewUserClient(@Valid @RequestBody ClientDto client)
            throws MessagingException,
            UnsupportedEncodingException {

        return ResponseEntity.status(HttpStatus.OK).body(clientService.registerNewUserClient(client));
    }

    @PostMapping("/uploadClientIdentities")
    //@PreAuthorize("hasRole('Agent')")
    public void uploadClientIdentity(@RequestParam("identity") MultipartFile[] identities) {
        try {
            List<String> fileNames = new ArrayList<>();
            Arrays.asList(identities).stream().forEach(file -> {
                clientService.save(file);
                fileNames.add(file.getOriginalFilename());
            });
        } catch (Exception e) {
            log.warn("could't not store identites",e);
        }
    }

    @GetMapping("/client/solde/{clientId}")
    //@PreAuthorize("hasRole('Client')")
    public ResponseEntity<Integer> ClientSolde(@PathVariable("clientId") Long clientId){
        return ResponseEntity.ok().body(clientService.getSolde(clientId));
    }


}
