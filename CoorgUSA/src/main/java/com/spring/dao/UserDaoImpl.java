package com.spring.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.model.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public User findById(int userid) {
		User user = getByKey(userid);
		if (user != null) {
			Hibernate.initialize(user.getUserid());
		}
		return user;
	}

	public User findByUserName(String username) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("username", username));
		User user = (User) crit.uniqueResult();
		if (user != null) {
			Hibernate.initialize(user);
		}
		return user;
	}

	public User findByFirstName(String firstname) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("firstname", firstname));
		User user = (User) crit.uniqueResult();
		if (user != null) {
			Hibernate.initialize(user);
		}
		return user;
	}

	public User findByEmail(String email) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("email", email));
		User user = (User) crit.uniqueResult();
		if (user != null) {
			Hibernate.initialize(user);
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<String> getUserList(String query) {
		List<String> matched = new ArrayList<String>();
		try {
			if (!sessionFactory.getCurrentSession().getTransaction().isActive()) {
				sessionFactory.getCurrentSession().getTransaction().begin();
			}
			Query querylist = sessionFactory.getCurrentSession()
					.createQuery("select u.firstname from User as u where u.firstname like '" + query + "%'");
			matched = querylist.list();
			query = query.toLowerCase();
			return matched;
		} catch (Exception e) {
			return null;
		}
	}

	public User findByLogin(String username, String password) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("username", username));
		crit.add(Restrictions.eq("password", password));
		User user = (User) crit.uniqueResult();
		if (user != null) {
			Hibernate.initialize(user);
		}
		return user;
	}

	public void setUserRole(User user, String role) {
		user.setRole(role);
		persist(user);
	}

	public void setUserStatus(User user, String status) {
		user.setStatus(status);
		persist(user);
	}

	public void saveUser(User user) {
		persist(user);
	}

	public void updateUserPassword(User user, String password) {
		user.setPassword(password);
		persist(user);
	}

	public void deleteById(int userid) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("userid", userid));
		User user = (User) crit.uniqueResult();
		delete(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstname"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<User> users = (List<User>) criteria.list();
		return users;
	}

}
