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
import org.springframework.transaction.annotation.Transactional;

import com.spring.model.Event;

@Repository("eventDao")
public class EventDaoImpl extends AbstractDao<Integer, Event> implements EventDao {

	static final Logger logger = LoggerFactory.getLogger(EventDaoImpl.class);

	@SuppressWarnings("unchecked")
	public List<Event> getAllEvents() {
		Criteria crit = createEntityCriteria().addOrder(Order.asc("date"));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Event> events = (List<Event>) crit.list();
		return events;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Event> getEvents(Integer offset, Integer maxResults) {
		Criteria crit = createEntityCriteria().addOrder(Order.asc("date"));
		crit.setFirstResult(offset != null ? offset : 0);
		crit.setMaxResults(maxResults != null ? maxResults : 10);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Event> events = (List<Event>) crit.list();
		return events;
	}

	public Long count() {
		return (Long) createEntityCriteria().setProjection(Projections.rowCount()).uniqueResult();
	}

	public void save(Event event) {
		persist(event);
	}

	public Event findById(int id) {
		Event event = getByKey(id);
		if (event != null) {
			Hibernate.initialize(event.getId());
		}
		return event;
	}

	public Event findEvent(String title, Date date, String location) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("title", title));
		crit.add(Restrictions.eq("date", date));
		crit.add(Restrictions.eq("location", location));
		Event event = (Event) crit.uniqueResult();
		if (event != null) {
			Hibernate.initialize(event);
		}
		return event;
	}

	public void deleteById(int id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		Event event = (Event) crit.uniqueResult();
		delete(event);
	}

}