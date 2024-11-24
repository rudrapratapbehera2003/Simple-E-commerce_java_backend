package com.dino.e_commerce.project.controller;

import com.dino.e_commerce.project.model.Users;
import com.dino.e_commerce.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
        UserService service;

    @PostMapping("/register")
    public Users register(@RequestBody Users user){
        return service.register(user);
    }
    @PostMapping("/login")
    public String login(@RequestBody Users user){
        return service.verify(user);
    }

}
