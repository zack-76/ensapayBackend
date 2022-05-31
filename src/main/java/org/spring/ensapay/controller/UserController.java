package org.spring.ensapay.controller;

import org.spring.ensapay.entity.User;
import org.spring.ensapay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }


    @PostMapping("/regiterNewUserClient")
    //@PreAuthorize("hasRole('Agent')")
    public String regiterNewUserClient(@RequestBody User user) throws MessagingException,
            UnsupportedEncodingException {

        return userService.registerNewUserClient(user);
    }

    @PostMapping("/regiterNewUserAgent")
    //@PreAuthorize("hasRole('BackOffice')")
    public String regiterNewUserAgent(@RequestBody User user) throws MessagingException,
            UnsupportedEncodingException {

        return userService.registerNewUserAgent(user);
    }


    @PutMapping("/resetpassword/{email}")
    @PreAuthorize("hasAnyRole('BackOffice','Agent','Client')")
    public String resetPassword(@PathVariable("email") String email,@RequestBody String userPassword){
        return userService.resetPassword(userPassword,email);
    }


    @GetMapping("/client/solde/{email}")
    //@PreAuthorize("hasRole('Client')")
    public Integer ClientSolde(@PathVariable("email") String email){
        return userService.getSolde(email);
    }

}
