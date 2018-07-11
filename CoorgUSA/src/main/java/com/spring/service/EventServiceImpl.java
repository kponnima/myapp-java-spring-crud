package com.spring.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.EventDao;
import com.spring.model.Event;

@Service("eventService")
@Transactional
public class EventServiceImpl implements EventService {

	@Autowired
	EventDao dao;

	public List<Event> getAllEvents() {
		return dao.getAllEvents();
	}

	public List<Event> getEvents(Integer offset, Integer maxResults) {
		return dao.getEvents(offset, maxResults);
	}

	public Long count() {
		return dao.count();
	}

	public void save(Event event) {
		dao.save(event);
	}

	public Event findById(int id) {
		return dao.findById(id);
	}

	public Event findEvent(String title, Date date, String location) {
		Event event = dao.findEvent(title, date, location);
		return event;
	}

	public void updateEvent(Event event) {
		Event entity = dao.findById(event.getId());
		if (entity != null) {
			entity.setTitle(event.getTitle());
			entity.setDate(event.getDate());
			entity.setLocation(event.getLocation());
		}
	}

	public void deleteById(int id) {
		dao.deleteById(id);
	}

	public boolean isEventUnique(Integer id, String title, Date date, String location) {
		Event event = findEvent(title, date, location);
		return (event == null || ((id != null) && (event.getId() == id)));
	}

}
