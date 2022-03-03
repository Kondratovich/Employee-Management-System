package com.company.springboot.service;

import com.company.springboot.repository.CustomerRepository;
import com.company.springboot.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;
}
