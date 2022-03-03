package com.company.springboot.controller;

import com.company.springboot.service.CustomerService;
import com.company.springboot.service.TeamService;
import org.springframework.stereotype.Controller;

@Controller
public class TeamController {

	private final TeamService teamService;

	public TeamController(TeamService teamService) {
		this.teamService = teamService;
	}
}
