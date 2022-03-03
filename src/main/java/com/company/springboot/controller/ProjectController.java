package com.company.springboot.controller;

import com.company.springboot.model.Project;
import com.company.springboot.service.CustomerService;
import com.company.springboot.service.ProjectService;
import org.springframework.stereotype.Controller;

@Controller
public class ProjectController {

	private final ProjectService projectService;

	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}


}
