package com.company.springboot.controller;

import com.company.springboot.dto.APOD;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.springboot.dto.UserDto;
import com.company.springboot.service.UserService;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.time.Duration;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private final UserService userService;
    private final RestTemplate restTemplate;

    public UserRegistrationController(UserService userService, RestTemplateBuilder restTemplateBuilder) {
        this.userService = userService;
        this.restTemplate = restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(500))
                .setReadTimeout(Duration.ofSeconds(500)).build();
    }

    @ModelAttribute("user")
    public UserDto userRegistrationDto() {
        return new UserDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        try {
            String url = "https://api.nasa.gov/planetary/apod?api_key=FMEdIH4F9acnCy9KtjPbYZ9VJJFSpdbB9kbJaYef";
            APOD response = restTemplate.getForObject(url, APOD.class);

            model.addAttribute("apodImg", response.getHdurl());
        } catch (Exception ex) {
        }

        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserDto registrationDto, BindingResult bindingResult, Model model) {

        try {
            String url = "https://api.nasa.gov/planetary/apod?api_key=FMEdIH4F9acnCy9KtjPbYZ9VJJFSpdbB9kbJaYef";
            APOD response = restTemplate.getForObject(url, APOD.class);

            model.addAttribute("apodImg", response.getHdurl());
        } catch (Exception ex) {
        }

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        if (userService.getEmployeeByEmail(registrationDto.getEmail()) != null) {
            return "redirect:/registration?alreadyExists";
        }

        if (!registrationDto.getPassword().equals(registrationDto.getRepeatedPassword())) {
            return "redirect:/registration?passwordsMismatch";
        }

        userService.save(registrationDto);

        return "redirect:/registration?success";
    }
}
