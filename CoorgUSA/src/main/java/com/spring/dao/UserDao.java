package com.spring.dao;

import java.util.List;

import com.spring.model.User;

public interface UserDao {

	User findById(int userid);

	User findByUserName(String username);

	User findByFirstName(String firstname);

	User findByEmail(String email);

	List<String> getUserList(String query);

	User findByLogin(String username, String password);

	void setUserRole(User user, String role);

	void setUserStatus(User user, String status);

	void saveUser(User user);

	void updateUserPassword(User user, String password);

	void deleteById(int userid);

	List<User> findAllUsers();

}