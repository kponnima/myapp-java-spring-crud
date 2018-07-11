package com.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.DislikeDao;
import com.spring.model.Dislike;

@Service("dislikeService")
@Transactional
public class DislikeServiceImpl implements DislikeService {

	@Autowired
	DislikeDao dao;

	public List<Dislike> getAllDislike() {
		return dao.getAllDislike();
	}

	public void save(Dislike dislike) {
		dao.save(dislike);
	}

	public Dislike findById(int id) {
		return dao.findById(id);
	}

	public Dislike findDislike(Integer userid, Integer postid) {
		Dislike dislike = dao.findDislike(userid, postid);
		return dislike;
	}

	public void deleteById(int id) {
		dao.deleteById(id);
	}

	public boolean isDislikeUnique(Integer userid, Integer postid) {
		Dislike dislike = findDislike(userid, postid);

		boolean check = true;
		if (dislike != null) {
			check = false;
		}
		return check;
	}

}