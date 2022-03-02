package com.company.springboot.service;

import com.company.springboot.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.company.springboot.model.User;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}
