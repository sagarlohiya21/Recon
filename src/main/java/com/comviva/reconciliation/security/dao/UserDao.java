package com.comviva.reconciliation.security.dao;

import com.comviva.reconciliation.security.entity.User;

public interface UserDao {
	User getUserByUserName(String username);
}
