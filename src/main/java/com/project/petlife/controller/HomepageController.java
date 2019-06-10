package com.project.petlife.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomepageController {


    @GetMapping("/")
    public String welcome() {
        return "homepage";
    }

}
