package com.cwpark.webstore.domain.repository;

import java.util.List;

import com.cwpark.webstore.domain.UserWS;

public interface UserRepository {
	List<UserWS> getAllUsers();
}