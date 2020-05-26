package com.manager.systems.webapi.wallet.service;

import java.util.Optional;

import com.manager.systems.webapi.wallet.entity.User;

public interface UserService {
	User save(User user);
	Optional<User> findByEmail(String email);
}