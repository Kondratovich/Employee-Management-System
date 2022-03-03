package com.company.springboot.service;

import com.company.springboot.repository.CustomerRepository;
import com.company.springboot.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
}
