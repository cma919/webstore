package com.cwpark.webstore.service;

import java.util.List;

import com.cwpark.webstore.domain.UserWS;

public interface UserService {
	List<UserWS> getAllUsers();
}