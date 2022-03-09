package com.company.springboot.controller;

import java.util.List;

import com.company.springboot.dto.UserRegistrationDto;
import com.company.springboot.model.User;
import com.company.springboot.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = {"/manager", "/user"})
public class EmployeeController {

    private final UserService userService;

    public EmployeeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/employees/showAddForm")
    public String showAddForm(Model model) {
        // create model attribute to bind form data
        UserRegistrationDto employee = new UserRegistrationDto();
        model.addAttribute("employee", employee);
        return "add_employee";
    }

    @PostMapping("/employees/save")
    public String save(@ModelAttribute("employee") UserRegistrationDto employee) {
        // save employee to database
        userService.save(employee);
        return "redirect:/manager/employees";
    }

    @GetMapping("/employees/showUpdateForm/{id}")
    public String showUpdateForm(@PathVariable(value = "id") long id, Model model) {

        // get employee from the service
        User employee = userService.getEmployeeById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("employee", employee);
        return "update_employee";
    }

    @GetMapping("/employees/delete/{id}")
    public String delete(@PathVariable(value = "id") long id) {

        // call delete employee method
        userService.deleteEmployeeById(id);
        return "redirect:/manager/employees";
    }

    @GetMapping("/employees")
    public String viewEmployeesPage(Model model) {
        return findPaginated(1, "firstName", "asc", model);
    }

    @GetMapping("/employees/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<User> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<User> listEmployees = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listEmployees", listEmployees);
        return "managerDashboard_employees";
    }
}
