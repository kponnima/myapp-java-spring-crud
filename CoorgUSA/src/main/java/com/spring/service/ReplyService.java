package com.spring.service;

import java.util.Date;
import java.util.List;

import com.spring.model.Reply;

public interface ReplyService {

	List<Reply> getAllReply();

	List<Reply> getPostReply(int postid);

	void save(Reply reply);

	Long replycount();

	Reply findById(int id);

	Reply findReply(String body, Date date);

	void deleteById(int id);

}
