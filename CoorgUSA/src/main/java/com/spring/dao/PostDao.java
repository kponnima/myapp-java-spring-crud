package com.spring.dao;

import java.util.List;

import com.spring.model.Post;

public interface PostDao {

	List<Post> getAllPosts();

	List<Post> getPosts(Integer offset, Integer maxResults);

	Long count();

	void save(Post post);

	Post findById(int postid);

	Post findPost(String title);

	void deleteById(int postid);

}
