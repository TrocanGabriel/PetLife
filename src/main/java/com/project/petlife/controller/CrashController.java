package com.project.petlife.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;

@Controller
public class CrashController  implements ErrorController {
//    @RequestMapping("/error")
//    public String handleError() {
//        //do something like logging
//        return "error";
//    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
