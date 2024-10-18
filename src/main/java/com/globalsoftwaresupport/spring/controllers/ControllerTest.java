package com.globalsoftwaresupport.spring.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTest {

    @GetMapping(value = "/friendo")
    public String sayHelloMyLilFriend() {
        return "testing";
    }
}
