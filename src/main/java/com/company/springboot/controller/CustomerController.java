package com.company.springboot.controller;

import com.company.springboot.model.Employee;
import com.company.springboot.service.CustomerService;
import com.company.springboot.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}


}
