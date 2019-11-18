package com.comviva.reconciliation.test;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.annotation.Rollback;

import com.comviva.reconciliation.security.dao.UserDaoImpl;
import com.comviva.reconciliation.security.entity.User;

@RunWith(MockitoJUnitRunner.class)
public class UserDaoTest {
	
	@InjectMocks
	private UserDaoImpl userDao;
	
	@Mock
	private EntityManager entityManagerMock;
	
	@Mock
	private Query query;
	
	User user;
	User fakeUser;
	
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(entityManagerMock);
		MockitoAnnotations.initMocks(query);	
		user = new User(102,"admin","pass");
		fakeUser = new User(103,"user","password");
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void getUserByUserName_Equals(){
		Mockito.when(entityManagerMock.createQuery(ArgumentMatchers.anyString())).thenReturn(query);
		Mockito.when(query.getSingleResult()).thenReturn(user);
		// when
        User actualUser = userDao.getUserByUserName("admin");
        // then
        assertThat(actualUser).isEqualTo(user);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void getUserByUserName_NotEquals(){
		Mockito.when(entityManagerMock.createQuery(ArgumentMatchers.anyString())).thenReturn(query);
		Mockito.when(query.getSingleResult()).thenReturn(user);
		// when
        User actualUser = userDao.getUserByUserName("admin");
        // then
        assertThat(actualUser).isNotEqualTo(fakeUser);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void getUserByUserName_QueryThrows_NoResultException(){
		Mockito.when(entityManagerMock.createQuery(ArgumentMatchers.anyString())).thenReturn(query);
		Mockito.when(query.getSingleResult()).thenThrow(NoResultException.class);
		// when
        User actualUser = userDao.getUserByUserName("admin");
        // then
        assertThat(actualUser).isEqualTo(null);
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void getUserByUserName_QueryThrows_NonUniqueResultException(){
		Mockito.when(entityManagerMock.createQuery(ArgumentMatchers.anyString())).thenReturn(query);
		Mockito.when(query.getSingleResult()).thenThrow(NonUniqueResultException.class);
		// when
        User actualUser = userDao.getUserByUserName("admin");
        // then
        assertThat(actualUser).isEqualTo(null);
	}
	
}
