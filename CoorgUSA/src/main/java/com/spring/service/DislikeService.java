package com.spring.service;

import java.util.List;

import com.spring.model.Dislike;

public interface DislikeService {

	List<Dislike> getAllDislike();

	void save(Dislike dislike);

	Dislike findById(int id);

	Dislike findDislike(Integer userid, Integer postid);

	void deleteById(int id);

	boolean isDislikeUnique(Integer userid, Integer postid);

}
