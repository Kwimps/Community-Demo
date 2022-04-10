package com.kwimps.community.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@RestController
public class testController {

    @GetMapping("/test")
    public String test(){
        return "test";
    }


}
