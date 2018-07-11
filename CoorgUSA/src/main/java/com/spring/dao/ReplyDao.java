package com.spring.dao;

import java.util.Date;
import java.util.List;

import com.spring.model.Reply;

public interface ReplyDao {

	List<Reply> getAllReply();

	List<Reply> getPostReply(int postid);

	void save(Reply reply);

	Long replycount();

	Reply findById(int replyid);

	Reply findReply(String body, Date date);

	void deleteById(int replyid);

}