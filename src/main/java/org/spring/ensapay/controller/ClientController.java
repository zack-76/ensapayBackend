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
    public void initClient(){clientService.initClient();}




    @PostMapping("/regiterNewUserClient")
    //@PreAuthorize("hasRole('Agent')")
    public ResponseEntity<String> uploadClientIdentity(@RequestParam(name = "file") MultipartFile[] identities,
    @RequestParam(name = "Company" ) String Company,
    @RequestParam(name = "Username") String Username,
    @RequestParam(name = "email") String email,
    @RequestParam(name = "FirstName") String FirstName,
    @RequestParam(name = "LastName") String LastName,
     @RequestParam(name = "Address") String Address,
     @RequestParam(name = "City") String City,
     @RequestParam(name = "Zip") String Zip,
     @RequestParam(name = "Country") String Country)throws MessagingException,
            UnsupportedEncodingException  {
        ClientDto clientDto=new ClientDto(FirstName,LastName,Address,email,Username,City,Zip,Country);
        try {
            Arrays.asList(identities).stream().forEach(file -> {
                clientService.save(file);

            });
        } catch (Exception e) {
            log.warn("could't not store identites",e);
        }
        clientService.registerNewUserClient(clientDto);
        return ResponseEntity.status(HttpStatus.OK).body("client added");
    }

    @GetMapping("/client/solde/{clientId}")
    //@PreAuthorize("hasRole('Client')")
    public ResponseEntity<Integer> ClientSolde(@PathVariable("clientId") Long clientId){
        return ResponseEntity.ok().body(clientService.getSolde(clientId));
    }


}
