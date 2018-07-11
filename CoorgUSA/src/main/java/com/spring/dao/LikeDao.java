package com.spring.dao;

import java.util.List;

import com.spring.model.Like;

public interface LikeDao {

	List<Like> getAllLike();

	void save(Like like);

	Like findById(int id);

	Like findLike(Integer userid, Integer postid);

	void deleteById(int id);
}