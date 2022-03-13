package com.company.springboot.controller;

import com.company.springboot.dto.TaskDto;
import com.company.springboot.dto.TeamDto;
import com.company.springboot.model.Project;
import com.company.springboot.model.Task;
import com.company.springboot.model.Team;
import com.company.springboot.model.User;
import com.company.springboot.service.ProjectService;
import com.company.springboot.service.TeamService;
import com.company.springboot.service.UserService;
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
public class TeamController {

    private final TeamService teamService;
    private final ProjectService projectService;
    private final UserService userService;

    public TeamController(TeamService teamService, ProjectService projectService, UserService userService) {
        this.teamService = teamService;
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/teams")
    public String viewTeamsPage(Model model) {

        List<Team> listTeams = teamService.getAllTeams();
        model.addAttribute("listTeams", listTeams);

        return "managerDashboard_teams";
    }

    @GetMapping("/teams/showAddForm")
    public String showAddForm(Model model) {

        TeamDto teamDto = new TeamDto();
        List<Project> listProjects = projectService.getAllProjectsWithoutTeam();
        List<User> listUsers = userService.getAllWithoutTeam();

        model.addAttribute("team", teamDto);
        model.addAttribute("listProjects", listProjects);
        model.addAttribute("listUsers", listUsers);

        return "managerDashboard_addTeam";
    }

    @PostMapping("/teams/save")
    public String save(@ModelAttribute("team") @Valid TeamDto teamDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            List<Project> listProjects = projectService.getAllProjectsWithoutTeam();
            List<User> listUsers = userService.getAllWithoutTeam();

            model.addAttribute("listProjects", listProjects);
            model.addAttribute("listUsers", listUsers);

            return "managerDashboard_addTeam";
        }

        Team team = new Team(
                teamDto.getOfficeLocation()
        );

        Long savedTeamId = teamService.saveTeam(team).getId();

        for (long projectId :
                teamDto.getProjectsIds()
        ) {
            Project project = projectService.getProjectById(projectId);
            project.setTeam(teamService.getTeamById(savedTeamId));
            projectService.saveProject(project);
        }

        for (long userId : teamDto.getUsersIds()
        ) {
            User user = userService.getEmployeeById(userId);
            user.setUserTeam(teamService.getTeamById((savedTeamId)));
            userService.edit(user);
        }

        return "redirect:/manager/teams/showAddForm?success";
    }
}
