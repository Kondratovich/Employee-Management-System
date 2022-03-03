package com.company.springboot.service;

import com.company.springboot.repository.CustomerRepository;
import com.company.springboot.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamRepository teamRepository;
}
