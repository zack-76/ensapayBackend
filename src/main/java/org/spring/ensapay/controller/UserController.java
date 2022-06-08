package org.spring.ensapay.controller;

import com.sun.jdi.event.ExceptionEvent;
import org.spring.ensapay.entity.*;
import org.spring.ensapay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;


    @PostMapping("/authenticate")
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return userService.createJwtToken(jwtRequest);
    }


    @PutMapping("/resetpassword/{username}")
    //@PreAuthorize("hasAnyRole('Agent','Client')")
    public ResponseEntity<String> resetPassword(@PathVariable("username") String username, @Valid @RequestBody User user){
      try {
          System.out.printf(user.getUserPassword()+"hhhhhhhhh");
          userService.resetPassword(user.getUserPassword(), username);
          return ResponseEntity.ok().body("password reset");
      }catch(Exception e){
          return ResponseEntity.status(400).body("cannot modify passwor ");
      }
    }


}
