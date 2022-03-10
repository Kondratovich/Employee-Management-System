package com.company.springboot.controller;

import com.company.springboot.dto.ProjectDto;
import com.company.springboot.model.Customer;
import com.company.springboot.model.Project;
import com.company.springboot.service.CustomerService;
import com.company.springboot.service.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping(value = {"/manager", "/user"})
public class ProjectController {

    private final ProjectService projectService;
    private final CustomerService customerService;

    public ProjectController(ProjectService projectService, CustomerService customerService) {
        this.projectService = projectService;
        this.customerService = customerService;
    }

    @GetMapping("/projects/showAddForm")
    public String showAddForm(Model model) {

        ProjectDto projectDto = new ProjectDto();
        Collection<Customer> customers = customerService.getAllCustomers();

        model.addAttribute("project", projectDto);
        model.addAttribute("listCustomers", customers);

        return "add_project";
    }

    @PostMapping("/projects/save")
    public String save(@ModelAttribute("project") @Valid ProjectDto projectDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            Collection<Customer> customers = customerService.getAllCustomers();
            model.addAttribute("listCustomers", customers);

            return "add_project";
        }

        Project project;
        if (projectDto.getId() != 0) {
            project = projectService.getProjectById(projectDto.getId());
        }
        else{
            project = new Project();
        }

        project.setCustomer(customerService.getCustomerById(projectDto.getCustomerId()));
        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        project.setDateAdded(projectDto.getDateAdded());
        project.setDeadline(projectDto.getDeadline());
        project.setPrice(projectDto.getPrice());

        projectService.saveProject(project);

        return "redirect:/manager/projects";
    }

    @GetMapping("/projects/showUpdateForm/{id}")
    public String showUpdateForm(@PathVariable(value = "id") long id, Model model) {

        Project project = projectService.getProjectById(id);
        Collection<Customer> customers = customerService.getAllCustomers();

        ProjectDto projectDto = new ProjectDto(
                project.getId(),
                project.getCustomer().getId(),
                project.getName(),
                project.getDescription(),
                project.getDateAdded(),
                project.getDeadline(),
                project.getPrice()
        );

        model.addAttribute("project", projectDto);
        model.addAttribute("listCustomers", customers);

        return "update_project";
    }

    @GetMapping("/projects/delete/{id}")
    public String delete(@PathVariable(value = "id") long id) {

        projectService.deleteProjectById(id);

        return "redirect:/manager/projects";
    }

    @GetMapping("/projects")
    public String viewProjectsPage(Model model) {

        return findPaginated(1, "name", "asc", model);
    }

    @GetMapping("/projects/tasks")
    public String viewProjectsTasksPage(Model model) {

        List<Project> listProjects = projectService.getAllProjects();
        model.addAttribute("listProjects", listProjects);

        return "managerDashboard_projectsTasks";
    }

    @GetMapping("/projects/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {

        int pageSize = 5;

        Page<Project> page = projectService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Project> listProjects = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listProjects", listProjects);

        return "managerDashboard_projects";
    }
}
