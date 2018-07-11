package com.spring.service;

import java.util.List;

import com.spring.model.Like;

public interface LikeService {

	List<Like> getAllLike();

	void save(Like like);

	Like findById(int id);

	Like findLike(Integer userid, Integer postid);

	void deleteById(int id);

	boolean isLikeUnique(Integer userid, Integer postid);

}
