package com.comviva.reconciliation.security.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.comviva.reconciliation.security.entity.User;

@Component
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entityManager;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

	@Override
	public User getUserByUserName(String username) throws NoResultException, NonUniqueResultException {
		LOGGER.info("Fetching User Details By User Name : " + username);
		Query query = entityManager.createQuery("from User where username=:username");
		query.setParameter("username", username);
		try {
			return (User) query.getSingleResult();
		} catch (Exception e) {
			LOGGER.debug("User with user name: " + username + " not fond in the Data Base");
			return null;
		}
	}

}
