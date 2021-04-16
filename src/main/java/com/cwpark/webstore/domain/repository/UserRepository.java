package com.cwpark.webstore.domain.repository;

import java.util.List;

import com.cwpark.webstore.domain.User;

public interface UserRepository {
	List<User> getAllUsers();
}