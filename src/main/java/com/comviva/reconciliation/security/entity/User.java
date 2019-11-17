package com.comviva.reconciliation.security.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_details")
public class User {
	@Id
	private int id;
	private String username;
	private String password;

	public User() {
		super();
	}

	public User(int id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	int getId() {
		return id;
	}

	void setId(int id) {
		this.id = id;
	}

	String getUsername() {
		return username;
	}

	void setUsername(String userName) {
		this.username = userName;
	}

	String getPassword() {
		return password;
	}

	void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "[ id : " + getId() + " username : " + getUsername() + " password : " + getPassword() + " ]";
	}

}
