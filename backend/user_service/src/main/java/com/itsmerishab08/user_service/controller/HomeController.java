package com.itsmerishab08.user_service.controller;

import com.itsmerishab08.user_service.entity.UserEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@RestController
public class HomeController {

    @RequestMapping(value = "/home_controller", method = RequestMethod.GET)
    public String home() {
        return "Welcome to the Home Controller!";
    }

    @RequestMapping(value = "/user_test", method = RequestMethod.GET)
    public UserEntity getUser() {
        UserEntity user = new UserEntity();
        user.setUserId(UUID.randomUUID().toString());
        user.setFullName("John Doe");
        user.setEmail("john.doe@gmail.com");
        user.setPhoneNumber("1234567890");
        user.setRole("ROLE_USER");
//        user.setCreatedAt(LocalDateTime.from(LocalTime.now()));
//        user.setUpdatedAt(LocalDateTime.now());
        return user;
    }
}
