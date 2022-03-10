package com.company.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.springboot.dto.UserDto;
import com.company.springboot.service.UserService;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private UserService userService;

    public UserRegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserDto userRegistrationDto() {
        return new UserDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserDto registrationDto) {

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
