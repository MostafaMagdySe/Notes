package com.example.demo.Controller;

import com.example.demo.DTO.CreateUserRequest;
import com.example.demo.Services.CreateUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class RegisterationController {

   private final CreateUserService createUserService;

   public RegisterationController(CreateUserService createUserService) {
        this.createUserService=createUserService;
   }


    @PostMapping("/register")
    public ResponseEntity CreateUsers (@RequestBody CreateUserRequest createUserRequest){


        createUserService.CreateUser(createUserRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
