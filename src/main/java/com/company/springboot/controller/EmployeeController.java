package com.company.springboot.controller;

import java.util.Collection;
import java.util.List;

import com.company.springboot.dto.UserDto;
import com.company.springboot.model.Position;
import com.company.springboot.model.Project;
import com.company.springboot.model.User;
import com.company.springboot.service.PositionService;
import com.company.springboot.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = {"/manager", "/user"})
public class EmployeeController {

    private final UserService userService;
    private final PositionService positionService;

    public EmployeeController(UserService userService, PositionService positionService) {
        this.userService = userService;
        this.positionService = positionService;
    }

    @GetMapping("/employees/showAddForm")
    public String showAddForm(Model model) {

        UserDto employee = new UserDto();
        Collection<Position> positions = positionService.getAllPositions();

        model.addAttribute("employee", employee);
        model.addAttribute("listPositions", positions);

        return "add_employee";
    }

    @PostMapping("/employees/save")
    public String save(@ModelAttribute("employee") @Valid UserDto employee, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            Collection<Position> positions = positionService.getAllPositions();
            model.addAttribute("listPositions", positions);

            return "add_employee";
        }

        if (employee.getPassword()!= null && !employee.getPassword().equals(employee.getRepeatedPassword())) {
            return "redirect:/manager/employees/showAddForm?passwordsMismatch";
        }

        if (employee.getId() != 0) {
            User user = userService.getEmployeeById(employee.getId());
            user.setPosition(positionService.getPositionById(employee.getPositionId()));
            user.setFirstName(employee.getFirstName());
            user.setLastName(employee.getLastName());
            user.setEmail(employee.getEmail());
            userService.edit(user);
        }
        else{
            userService.save(employee);
        }

        return "redirect:/manager/employees";
    }

    @GetMapping("/employees/showUpdateForm/{id}")
    public String showUpdateForm(@PathVariable(value = "id") long id, Model model) {

        User user = userService.getEmployeeById(id);
        UserDto userDto = new UserDto(
                user.getId(),
                user.getPosition().getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword()
        );

        Collection<Position> positions = positionService.getAllPositions();

        model.addAttribute("employee", userDto);
        model.addAttribute("listPositions", positions);

        return "update_employee";
    }

    @GetMapping("/employees/delete/{id}")
    public String delete(@PathVariable(value = "id") long id) {

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
