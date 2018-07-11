package com.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.spring.model.Like;

@Repository("likeDao")
public class LikeDaoImpl extends AbstractDao<Integer, Like> implements LikeDao {

	static final Logger logger = LoggerFactory.getLogger(LikeDaoImpl.class);

	@SuppressWarnings("unchecked")
	public List<Like> getAllLike() {
		Criteria crit = createEntityCriteria().addOrder(Order.desc("date"));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Like> like = (List<Like>) crit.list();
		return like;
	}

	public void save(Like like) {
		persist(like);
	}

	public Like findById(int id) {
		Like like = getByKey(id);
		if (like != null) {
			Hibernate.initialize(like.getId());
		}
		return like;
	}

	public Like findLike(Integer userid, Integer postid) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("user.userid", userid));
		crit.add(Restrictions.eq("post.postid", postid));
		Like like = (Like) crit.uniqueResult();
		return like;
	}

	public void deleteById(int id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		Like like = (Like) crit.uniqueResult();
		delete(like);
	}

}