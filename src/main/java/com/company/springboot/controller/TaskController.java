package com.company.springboot.controller;

import com.company.springboot.service.TaskService;
import org.springframework.stereotype.Controller;

@Controller
public class TaskController {

	private final TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
}
