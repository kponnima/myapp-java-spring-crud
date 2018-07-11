package com.spring.dao;

import java.util.Date;
import java.util.List;

import com.spring.model.Event;

public interface EventDao {

	List<Event> getAllEvents();

	List<Event> getEvents(Integer offset, Integer maxResults);

	Long count();

	void save(Event events);

	Event findById(int id);

	Event findEvent(String title, Date date, String location);

	void deleteById(int id);
}
