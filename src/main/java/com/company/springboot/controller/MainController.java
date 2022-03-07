package com.company.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home() {
        return "startpage";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
