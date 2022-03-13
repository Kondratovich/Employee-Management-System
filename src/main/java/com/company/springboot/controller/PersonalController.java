package com.company.springboot.controller;

import com.company.springboot.dto.UserDto;
import com.company.springboot.model.User;
import com.company.springboot.service.PositionService;
import com.company.springboot.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class PersonalController {

    private final UserService userService;
    private final PositionService positionService;

    public PersonalController(UserService userService, PositionService positionService) {
        this.userService = userService;
        this.positionService = positionService;
    }

    @GetMapping("/updatePersonalInformation")
    public String updatePersonalInformation(Model model) {
        org.springframework.security.core.userdetails.User authUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getEmployeeByEmail(authUser.getUsername());

        UserDto userDto = new UserDto(
                user.getId(),
                user.getPosition().getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword()
        );

        model.addAttribute("employee", userDto);

        return "updatePersonalInformation";
    }

    @PostMapping("/updatePersonalInformation")
    public String updatePersonalInformation(@ModelAttribute("employee") @Valid UserDto employee, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "updatePersonalInformation";
        }

        User user = userService.getEmployeeById(employee.getId());
        user.setFirstName(employee.getFirstName());
        user.setLastName(employee.getLastName());
        userService.edit(user);

        if (user.getEmail().equals("admin")) {
            return "redirect:/manager/projects";
        }
        else{
            return "redirect:/user/tasks";
        }
    }
}
