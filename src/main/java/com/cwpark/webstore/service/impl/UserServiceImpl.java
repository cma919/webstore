
package com.cwpark.webstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwpark.webstore.domain.User;
import com.cwpark.webstore.domain.repository.UserRepository;
import com.cwpark.webstore.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired UserRepository userRepository;
	@Override
	public List<User> getAllUsers() {
		return userRepository.getAllUsers();
	}
}