package com.company.springboot.controller;

import com.company.springboot.model.User;
import com.company.springboot.service.TaskService;
import com.company.springboot.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final TaskService taskService;

    public UserController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/team")
    public String viewTeamPage(Model model) {
        org.springframework.security.core.userdetails.User authUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getEmployeeByEmail(authUser.getUsername());

        model.addAttribute("user", user);

        return "userDashboard_teamInfo";
    }

    @GetMapping("/projects")
    public String viewProjectsPage(Model model) {
        org.springframework.security.core.userdetails.User authUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getEmployeeByEmail(authUser.getUsername());

        model.addAttribute("user", user);

        return "userDashboard_projectsInfo";
    }

    @GetMapping("/tasks")
    public String viewTasksPage(Model model) {
        org.springframework.security.core.userdetails.User authUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getEmployeeByEmail(authUser.getUsername());

        model.addAttribute("user", user);

        return "userDashboard_tasks";
    }

    @GetMapping("/tasks/markAsDone/{id}")
    public String markAsDone(@PathVariable(value = "id") long id) {

        taskService.markTaskAsDone(id);

        return "redirect:/user/tasks";
    }
}
