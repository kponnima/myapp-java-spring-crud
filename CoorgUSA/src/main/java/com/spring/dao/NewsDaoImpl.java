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

import com.spring.model.News;

@Repository("newsDao")
public class NewsDaoImpl extends AbstractDao<Integer, News> implements NewsDao {

	static final Logger logger = LoggerFactory.getLogger(NewsDaoImpl.class);

	@SuppressWarnings("unchecked")
	public List<News> getAllNews() {
		Criteria crit = createEntityCriteria().addOrder(Order.desc("date"));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<News> news = (List<News>) crit.list();
		return news;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<News> getNews(Integer offset, Integer maxResults) {
		Criteria crit = createEntityCriteria().addOrder(Order.desc("date"));
		crit.setFirstResult(offset != null ? offset : 0);
		crit.setMaxResults(maxResults != null ? maxResults : 5);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<News> news = (List<News>) crit.list();
		return news;
	}

	public Long count() {
		return (Long) createEntityCriteria().setProjection(Projections.rowCount()).uniqueResult();
	}

	public void save(News news) {
		persist(news);
	}

	public News findById(int newsid) {
		News news = getByKey(newsid);
		if (news != null) {
			Hibernate.initialize(news.getNewsid());
		}
		return news;
	}

	public News findNews(String title) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("title", title));
		News news = (News) crit.uniqueResult();
		if (news != null) {
			Hibernate.initialize(news);
		}
		return news;
	}

	public void deleteById(int newsid) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("newsid", newsid));
		News news = (News) crit.uniqueResult();
		delete(news);
	}
}