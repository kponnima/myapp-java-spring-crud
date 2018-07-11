package com.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.dao.UserDao;
import com.spring.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	MailService mailService;

	public User findById(int id) {
		return dao.findById(id);
	}

	public User findByUserName(String username) {
		User user = dao.findByUserName(username);
		return user;
	}

	public User findByFirstName(String firstname) {
		User user = dao.findByFirstName(firstname);
		return user;
	}

	public User findByEmail(String email) {
		User user = dao.findByEmail(email);
		return user;
	}

	public List<String> getUserList(String firstname) {
		List<String> users = dao.getUserList(firstname);
		return users;
	}

	/*
	 * @SuppressWarnings("unchecked") public List<String> getUserList(String
	 * query) {
	 * 
	 * List<String> users = new ArrayList<String>();
	 * 
	 * Query queryList = sessionFactory.getCurrentSession()
	 * .createQuery("Select u.firstname FROM users u WHERE u.firstname LIKE '%"
	 * + query + "%'"); users = queryList.list(); query = query.toLowerCase();
	 * 
	 * return users; }
	 */

	public User findByLogin(String username, String password) {
		User user = dao.findByLogin(username, password);
		return user;
	}

	public void saveUser(User user) {
		user.setUsername(user.getUsername());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setFirstname(user.getFirstname());
		user.setLastname(user.getLastname());
		user.setEmail(user.getEmail());
		user.setRole(user.getRole());
		user.setStatus(user.getStatus());
		dao.saveUser(user);
	}

	public void updateUserPassword(User user, String password) {
		password = passwordEncoder.encode(password);
		dao.updateUserPassword(user, password);
	}

	@Override
	public void sendRegistrationConfirmation(User user) {
		mailService.sendRegistrationEmail(user);
	}

	public void updateUser(User user) {
		User entity = dao.findById(user.getUserid());
		if (entity != null) {
			entity.setUsername(user.getUsername());
			entity.setFirstname(user.getFirstname());
			entity.setLastname(user.getLastname());
			entity.setEmail(user.getEmail());
			entity.setRole(user.getRole());
			entity.setStatus(user.getStatus());
		}
	}

	public void deleteById(int userid) {
		dao.deleteById(userid);
	}

	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

	public boolean isUserNameUnique(Integer userid, String username) {
		User user = findByUserName(username);
		return (user == null || ((userid != null) && (user.getUserid() == userid)));
	}

	public boolean isUserEmailUnique(String email) {
		User user = findByEmail(email);

		boolean check = false;
		if (user != null) {
			check = true;
		}
		return check;
	}

}