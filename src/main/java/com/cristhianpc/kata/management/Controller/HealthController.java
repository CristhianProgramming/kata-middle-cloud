package com.cristhianpc.kata.management.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/")
public class HealthController {

    @GetMapping("health")
    String getHealth(){
        return "all is right";
    }
}
