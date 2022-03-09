package com.company.springboot.service;

import com.company.springboot.dto.UserRegistrationDto;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.company.springboot.model.User;

import java.util.List;

public interface UserService extends UserDetailsService{
	List<User> getAllEmployees();

	User getEmployeeById(long id);

	void deleteEmployeeById(long id);

	Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

	User save(UserRegistrationDto registrationDto);
}
