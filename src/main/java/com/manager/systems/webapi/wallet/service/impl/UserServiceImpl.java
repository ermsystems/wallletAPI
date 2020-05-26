package com.manager.systems.webapi.wallet.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.systems.webapi.wallet.entity.User;
import com.manager.systems.webapi.wallet.repository.UserRepository;
import com.manager.systems.webapi.wallet.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repository;

	@Override
	public User save(final User user) {
		return this.repository.save(user);
	}

	@Override
	public Optional<User> findByEmail(final String email) {
		return this.repository.findByEmailEquals(email);
	}
}
