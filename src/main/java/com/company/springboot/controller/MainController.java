package com.company.springboot.controller;

import com.company.springboot.dto.APOD;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Controller
public class MainController {

    private final RestTemplate restTemplate;

    public MainController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(500))
                .setReadTimeout(Duration.ofSeconds(500)).build();
    }

    @GetMapping("/")
    public String home(Model model) {
        try {
            String url = "https://api.nasa.gov/planetary/apod?api_key=FMEdIH4F9acnCy9KtjPbYZ9VJJFSpdbB9kbJaYef";
            APOD response = restTemplate.getForObject(url, APOD.class);

            model.addAttribute("apodImg", response.getHdurl());
        } catch (Exception ex) {
        }

        return "startpage";
    }

    @GetMapping("/login")
    public String login(Model model) {
        try {
            String url = "https://api.nasa.gov/planetary/apod?api_key=FMEdIH4F9acnCy9KtjPbYZ9VJJFSpdbB9kbJaYef";
            APOD response = restTemplate.getForObject(url, APOD.class);

            model.addAttribute("apodImg", response.getHdurl());
        } catch (Exception ex) {
        }

        return "login";
    }

}
