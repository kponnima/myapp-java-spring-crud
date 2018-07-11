package com.spring.dao;

import java.util.List;

import com.spring.model.Dislike;

public interface DislikeDao {

	List<Dislike> getAllDislike();

	void save(Dislike dislike);

	Dislike findById(int id);

	Dislike findDislike(Integer userid, Integer postid);

	void deleteById(int id);

}