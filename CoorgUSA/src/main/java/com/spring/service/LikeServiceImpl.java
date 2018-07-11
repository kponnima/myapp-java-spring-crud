package com.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.LikeDao;
import com.spring.model.Like;

@Service("likeService")
@Transactional
public class LikeServiceImpl implements LikeService {

	@Autowired
	LikeDao dao;

	public List<Like> getAllLike() {
		return dao.getAllLike();
	}

	public void save(Like like) {
		dao.save(like);
	}

	public Like findById(int id) {
		return dao.findById(id);
	}

	public Like findLike(Integer userid, Integer postid) {
		Like like = dao.findLike(userid, postid);
		return like;
	}

	public void deleteById(int id) {
		dao.deleteById(id);
	}

	public boolean isLikeUnique(Integer userid, Integer postid) {
		Like like = findLike(userid, postid);

		boolean check = true;
		if (like != null) {
			check = false;
		}
		return check;
	}

}
