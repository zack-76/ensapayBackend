package org.spring.ensapay.controller;

import lombok.extern.slf4j.Slf4j;
import org.spring.ensapay.entity.User;
import org.spring.ensapay.service.ForgePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
public class ForgetPasswordController {

    @Autowired
    private ForgePasswordService forgePasswordService;


    @PostMapping(path = "/forgetPassword/{username}")
    public ResponseEntity<String> changePassword(@PathVariable(value ="username" ) String username) {
        try {
            this.forgePasswordService.ForgetPassword(username);
            return ResponseEntity.ok().body("We have sent a token to verify your identity. Please check you email!");
        }catch (Exception e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/forgetPassword/checkToken/{username}/{token}")
    public ResponseEntity<String> changePassword(@PathVariable(value ="username" ) String username,@PathVariable(value ="token" ) String token){
        if(this.forgePasswordService.checkToken(username,token)){
            return ResponseEntity.ok().body("success");
        }
        return ResponseEntity.status(400).body("Incorrect Token! check please");
    }

    @PostMapping("/forgetPassword/newPassword/{username}")
    public ResponseEntity<String> NewPassword(@PathVariable(value ="username" ) String username, @RequestBody User user){
        try {
            this.forgePasswordService.newPassword(username,user.getUserPassword());
            return   ResponseEntity.ok().body("password changed");
        }catch (Exception e){
           return ResponseEntity.status(400).body(e.getMessage());
        }


    }

}

