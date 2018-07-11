package com.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.spring.model.Dislike;

@Repository("dislikeDao")
public class DislikeDaoImpl extends AbstractDao<Integer, Dislike> implements DislikeDao {

	static final Logger logger = LoggerFactory.getLogger(DislikeDaoImpl.class);

	@SuppressWarnings("unchecked")
	public List<Dislike> getAllDislike() {
		Criteria crit = createEntityCriteria().addOrder(Order.desc("date"));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Dislike> dislike = (List<Dislike>) crit.list();
		return dislike;
	}

	public void save(Dislike dislike) {
		persist(dislike);
	}

	public Dislike findById(int id) {
		Dislike dislike = getByKey(id);
		if (dislike != null) {
			Hibernate.initialize(dislike.getId());
		}
		return dislike;
	}

	public Dislike findDislike(Integer userid, Integer postid) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("user.userid", userid));
		crit.add(Restrictions.eq("post.postid", postid));
		Dislike dislike = (Dislike) crit.uniqueResult();
		return dislike;
	}

	public void deleteById(int id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		Dislike dislike = (Dislike) crit.uniqueResult();
		delete(dislike);
	}

}