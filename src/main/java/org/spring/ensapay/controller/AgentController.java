package org.spring.ensapay.controller;

import lombok.extern.slf4j.Slf4j;
import org.spring.ensapay.dto.AgentDto;
import org.spring.ensapay.entity.Agent;
import org.spring.ensapay.entity.Client;
import org.spring.ensapay.entity.Facture;
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
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/agent")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @Autowired
    private UserService userService;


  //  @PostConstruct
    //public void initAgent() {
      //  agentService.initAgent();
    //}



    @PostMapping("/regiterNewUserAgent/{id}")
    @PreAuthorize("hasRole('Backoffice')")

    public ResponseEntity<String> regiterNewUserAgent(@RequestParam("file") MultipartFile[] identities,
                                                      @RequestParam("agentPhone") String agentPhone,
                                                      @RequestParam("agentFirstName") String agentFirstName,
                                                      @RequestParam("agentLastName") String agentLastName,
                                                      @RequestParam("agentAddress") String agentAddress,
                                                      @RequestParam("agentCIN") String agentCIN,
                                                      @RequestParam("agentEmail") String agentEmail,
                                                      @RequestParam("agentCity") String agentCity,
                                                      @RequestParam("agentZip") String agentZip,
                                                      @RequestParam("agentCountry") String agentCountry,
                                                      @RequestParam("agentusername") String agenusername,
                                                      @PathVariable(value = "id") Long id)
            throws Exception {

        @Valid AgentDto agentDto = new AgentDto(agentPhone, agentFirstName, agentLastName, agentAddress, agentCIN, agentEmail, agentCity, agentZip, agentCountry, agenusername,id);

        try {
            agentService.registerNewUserAgent(agentDto);
            Arrays.asList(identities).stream().forEach(file -> {
                agentService.save(file);
            });
            log.info("Agent" + agentDto.getAgentFirstName() + " " + agentDto.getAgentLastName() + "successfully added");
            return ResponseEntity.status(HttpStatus.OK).body("added");

        } catch (Exception e) {
            log.warn("could't not store identites", e);
           return ResponseEntity.status(400).body(e.getMessage());
        }

    }



    @GetMapping("/profileAgent/{username}")
    public @ResponseBody
    Agent getAgent(@PathVariable(value  = "username") String username) {
        return this.agentService.getAgentProfile(username);
    }
    @GetMapping("/getClients/{id}")
    @PreAuthorize("hasRole('Agent')")
    public ResponseEntity<List<Client>> getAllClient(
                                                        @PathVariable("id") Long id){
        return ResponseEntity.status(200).body(this.agentService.getClients(id));
    }
    @GetMapping("/getClients/{id}/{client}")
    @PreAuthorize("hasRole('Agent')")
    public ResponseEntity<List<Client>> getAllClientwithSearch(
            @PathVariable("id") Long id,@PathVariable("client") String name){
        return ResponseEntity.status(200).body(this.agentService.getClients(id,name));
    }


}
