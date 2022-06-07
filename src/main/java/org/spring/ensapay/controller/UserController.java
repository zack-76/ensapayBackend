package org.spring.ensapay.controller;

import lombok.extern.slf4j.Slf4j;
import org.spring.ensapay.entity.*;
import org.spring.ensapay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public JwtResponse createJwtToken(@RequestBody @Valid JwtRequest jwtRequest) throws Exception {
        log.info("User "+jwtRequest.getUsername()+" has authenticate");
        return userService.createJwtToken(jwtRequest);
    }


    @PutMapping("/resetpassword/{username}")
    @PreAuthorize("hasAnyRole('Agent','Client')")
    public String resetPassword(@PathVariable("username") String username,@Valid @RequestBody String userPassword){
        log.info("User "+username+" has reset he's Password");
        return userService.resetPassword(userPassword,username);
    }


}
