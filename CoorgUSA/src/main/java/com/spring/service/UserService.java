package com.spring.service;

import java.util.List;

import com.spring.model.User;

public interface UserService {

	User findById(int userid);

	User findByUserName(String username);

	User findByFirstName(String firstname);

	User findByEmail(String email);

	public List<String> getUserList(String firstname);

	User findByLogin(String username, String password);

	void saveUser(User user);

	void updateUserPassword(User user, String password);

	public void sendRegistrationConfirmation(User user);

	void updateUser(User user);

	void deleteById(int userid);

	List<User> findAllUsers();

	boolean isUserNameUnique(Integer userid, String username);

	boolean isUserEmailUnique(String email);

}