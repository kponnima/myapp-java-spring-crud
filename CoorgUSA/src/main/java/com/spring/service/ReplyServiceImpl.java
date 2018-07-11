package com.spring.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.ReplyDao;
import com.spring.model.Reply;

@Service("replyService")
@Transactional
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	ReplyDao dao;

	public List<Reply> getAllReply() {
		return dao.getAllReply();
	}

	public List<Reply> getPostReply(int postid) {
		return dao.getPostReply(postid);
	}

	public void save(Reply reply) {
		dao.save(reply);
	}

	public Long replycount() {
		return dao.replycount();
	}

	public Reply findById(int id) {
		return dao.findById(id);
	}

	public Reply findReply(String body, Date date) {
		Reply reply = dao.findReply(body, date);
		return reply;
	}

	public void deleteById(int id) {
		dao.deleteById(id);
	}

}
