package org.spring.ensapay.controller;

import org.spring.ensapay.entity.*;
import org.spring.ensapay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/authenticate")
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return userService.createJwtToken(jwtRequest);
    }


    @PutMapping("/resetpassword/{username}")
    //@PreAuthorize("hasAnyRole('Agent','Client')")
    public String resetPassword(@PathVariable("username") String username,@RequestBody String userPassword){
        return userService.resetPassword(userPassword,username);
    }


}
