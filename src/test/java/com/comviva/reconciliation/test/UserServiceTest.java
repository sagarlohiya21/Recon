package com.comviva.reconciliation.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.comviva.reconciliation.security.dao.UserDao;
import com.comviva.reconciliation.security.entity.User;
import com.comviva.reconciliation.security.entity.UserPrinciple;
import com.comviva.reconciliation.security.service.UserDetailsServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	@InjectMocks
	private UserDetailsServiceImpl userService;
	
	@Mock
	private UserDao userDao;
	
	User user;
	User fakeUser;
	UserPrinciple fakeUserPrinciple;
	UserPrinciple userPrinciple;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(userDao);
		user = new User(102,"admin","pass");
		fakeUser = new User(103,"user","password");
		userPrinciple = new UserPrinciple(user);
		fakeUserPrinciple = new UserPrinciple(fakeUser);
	}
	
	@Test
	public void loadByUserName_EqualsTo_Required() {

		Mockito.when(userDao.getUserByUserName(ArgumentMatchers.anyString())).thenReturn(user);
        // when
        UserPrinciple actualuserDetails = (UserPrinciple) userService.loadUserByUsername("admin");
        // then
        assertThat(actualuserDetails.getPassword()).isEqualTo(userPrinciple.getPassword());	
        assertThat(actualuserDetails.getUsername()).isEqualTo(userPrinciple.getUsername());	
	}
	
	@Test
	public void loadByUserName_NotEquals() {

		Mockito.when(userDao.getUserByUserName(ArgumentMatchers.anyString())).thenReturn(fakeUser);
        // when
        UserPrinciple actualuserDetails = (UserPrinciple) userService.loadUserByUsername("admin");
        // then
        assertThat(actualuserDetails.getPassword()).isNotEqualTo(userPrinciple.getPassword());	
        assertThat(actualuserDetails.getUsername()).isNotEqualTo(userPrinciple.getUsername());	
	}
	
	@Test
	public void loadByUserName_UsernameNotFoundException() {

		Mockito.when(userDao.getUserByUserName(ArgumentMatchers.anyString())).thenReturn(null);
        // when
        
        assertThrows(UsernameNotFoundException.class, () -> {
        	UserPrinciple actualuserDetails = (UserPrinciple) userService.loadUserByUsername("admin");
	    });;		
	}
	
}
