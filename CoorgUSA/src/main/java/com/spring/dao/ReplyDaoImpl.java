package com.spring.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.spring.model.Reply;

@Repository("replyDao")
public class ReplyDaoImpl extends AbstractDao<Integer, Reply> implements ReplyDao {

	static final Logger logger = LoggerFactory.getLogger(ReplyDaoImpl.class);

	@SuppressWarnings("unchecked")
	public List<Reply> getAllReply() {
		Criteria crit = createEntityCriteria().addOrder(Order.desc("date"));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Reply> reply = (List<Reply>) crit.list();
		return reply;
	}

	@SuppressWarnings("unchecked")
	public List<Reply> getPostReply(int postid) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("post.postid", postid));
		crit.addOrder(Order.desc("date"));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Reply> reply = (List<Reply>) crit.list();
		return reply;
	}

	public void save(Reply reply) {
		persist(reply);
	}

	public Long replycount() {
		return (Long) createEntityCriteria().setProjection(Projections.rowCount()).uniqueResult();
	}

	public Reply findById(int replyid) {
		Reply reply = getByKey(replyid);
		if (reply != null) {
			Hibernate.initialize(reply.getReplyid());
		}
		return reply;
	}

	public Reply findReply(String body, Date date) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("body", body));
		crit.add(Restrictions.eq("date", date));
		Reply reply = (Reply) crit.uniqueResult();
		if (reply != null) {
			Hibernate.initialize(reply);
		}
		return reply;
	}

	public void deleteById(int replyid) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("replyid", replyid));
		Reply reply = (Reply) crit.uniqueResult();
		delete(reply);
	}

}