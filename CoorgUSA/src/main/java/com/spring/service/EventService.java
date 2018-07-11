package com.spring.service;

import java.util.Date;
import java.util.List;

import com.spring.model.Event;

public interface EventService {

	List<Event> getAllEvents();

	List<Event> getEvents(Integer offset, Integer maxResults);

	Long count();

	void save(Event event);

	Event findById(int id);

	Event findEvent(String title, Date date, String location);

	void updateEvent(Event event);

	void deleteById(int id);

	boolean isEventUnique(Integer id, String title, Date date, String location);
}
