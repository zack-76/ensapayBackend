package org.spring.ensapay.controller;

import com.sun.jdi.event.ExceptionEvent;
import lombok.extern.slf4j.Slf4j;
import org.spring.ensapay.entity.*;
import org.spring.ensapay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @PutMapping("/changePassword/{username}")
    //@PreAuthorize("hasAnyRole('Agent','Client')")
    public ResponseEntity<String> ChangePassword(@PathVariable("username") String username, @Valid @RequestBody Password password){
        try {
            System.out.println(password.getPassword()+"  "+password.getConfirmPassword()+"  "+username);

            userService.ChangePassword(password.getPassword(),password.getConfirmPassword(), username);
            return ResponseEntity.ok().body("Password changed");
        }catch(Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
//=======
//    @PreAuthorize("hasAnyRole('Agent','Client')")
//    public String resetPassword(@PathVariable("username") String username,@Valid @RequestBody User user){
//        log.info("User "+username+" has reset he's Password");
//        return userService.resetPassword(user.getUserPassword(),username);
//>>>>>>> 406aee6936b062218d0a666d91d7bcd243f15fa5
    }


}
