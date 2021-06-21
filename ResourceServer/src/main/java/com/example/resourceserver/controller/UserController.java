package com.example.resourceserver.controller;

import com.example.resourceserver.response.UserRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private Environment env;

    @GetMapping("/status/check")
    public String userStatusCheck(){
        System.out.println("running with resource server on port :"+ env.getProperty("local.server.port"));
        return "User status check running on port :"+ env.getProperty("local.server.port");
    }


    @GetMapping
    public String check(){
        return "Checking success";
    }
    @PreAuthorize("#id == #jwt.subject ")
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") String id, @AuthenticationPrincipal Jwt jwt){
         String data;
        return "Deleting success"+id +" with jwt sub "+ jwt.getSubject();
    }

    @PostAuthorize("returnObject.getUserId()== #jwt.getSubject()")
    @PostMapping("/{id}")
    public UserRest getUser(@PathVariable("id") String id, @AuthenticationPrincipal Jwt jwt){
        return  UserRest.builder()
                .firstName("dieulinh")
                .lastName("Nguoiwf yeu anh tan day ")
                .userId(jwt.getSubject())
                .build();
    }
}
