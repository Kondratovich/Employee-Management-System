package com.company.springboot.controller;

import com.company.springboot.dto.TaskDto;
import com.company.springboot.model.Customer;
import com.company.springboot.model.Project;
import com.company.springboot.model.Task;
import com.company.springboot.service.ProjectService;
import com.company.springboot.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = {"/manager"})
public class TaskController {

    private final TaskService taskService;
    private final ProjectService projectService;

    public TaskController(TaskService taskService, ProjectService projectService) {
        this.taskService = taskService;
        this.projectService = projectService;
    }

    @GetMapping("/tasks/showAddForm")
    public String showAddForm(Model model) {

        TaskDto task = new TaskDto();
        List<Project> listProjects = projectService.getAllProjects();

        model.addAttribute("task", task);
        model.addAttribute("listProjects", listProjects);

        return "managerDashboard_addTask";
    }

    @PostMapping("/tasks/save")
    public String save(@ModelAttribute("task") @Valid TaskDto taskDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            List<Project> listProjects = projectService.getAllProjects();
            model.addAttribute("listProjects", listProjects);

            return "managerDashboard_addTask";
        }

        Task task = new Task(
                projectService.getProjectById(taskDto.getProjectId()),
                taskDto.getName(),
                taskDto.getDescription(),
                taskDto.getStatus()
        );

        taskService.saveTask(task);

        return "redirect:/manager/tasks/showAddForm?success";
    }
}
