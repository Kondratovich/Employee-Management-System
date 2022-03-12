package com.company.springboot.service;

import com.company.springboot.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.company.springboot.model.User;

import java.util.List;

public interface UserService extends UserDetailsService{
	List<User> getAllEmployees();

	List<User> getAllWithoutTeam();

	User getEmployeeById(long id);

	User getEmployeeByEmail(String email);

	void deleteEmployeeById(long id);

	Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

	User save(UserDto registrationDto);

	User edit(User user);
}
