package com.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.model.Post;

@Repository("postDao")
public class PostDaoImpl extends AbstractDao<Integer, Post> implements PostDao {

	static final Logger logger = LoggerFactory.getLogger(PostDaoImpl.class);

	@SuppressWarnings("unchecked")
	public List<Post> getAllPosts() {
		Criteria crit = createEntityCriteria().addOrder(Order.desc("date"));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Post> posts = (List<Post>) crit.list();
		return posts;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Post> getPosts(Integer offset, Integer maxResults) {
		Criteria crit = createEntityCriteria().addOrder(Order.desc("date"));
		crit.setFirstResult(offset != null ? offset : 0);
		crit.setMaxResults(maxResults != null ? maxResults : 5);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Post> posts = (List<Post>) crit.list();
		return posts;
	}

	public Long count() {
		return (Long) createEntityCriteria().setProjection(Projections.rowCount()).uniqueResult();
	}

	public void save(Post post) {
		persist(post);
	}

	public Post findById(int postid) {
		Post post = getByKey(postid);
		if (post != null) {
			Hibernate.initialize(post.getPostid());
		}
		return post;
	}

	public Post findPost(String title) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("title", title));
		Post post = (Post) crit.uniqueResult();
		if (post != null) {
			Hibernate.initialize(post);
		}
		return post;
	}

	public void deleteById(int postid) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("postid", postid));
		Post post = (Post) crit.uniqueResult();
		delete(post);
	}

}