package com.comviva.reconciliation.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.comviva.reconciliation.security.dao.UserDao;
import com.comviva.reconciliation.security.entity.User;
import com.comviva.reconciliation.security.entity.UserPrinciple;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LOGGER.info("Laoding User by user Name...");
		User user = userDao.getUserByUserName(username);
		if (user == null) {
			LOGGER.error("User Details with user name : " + username + " not foud");
			throw new UsernameNotFoundException("username not found");
		}
		return new UserPrinciple(user);
	}

}
