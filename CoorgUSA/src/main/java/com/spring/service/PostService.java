package com.spring.service;

import java.util.List;

import com.spring.model.Post;

public interface PostService {

	List<Post> getAllPosts();

	List<Post> getPosts(Integer offset, Integer maxResults);

	Long count();

	void save(Post post);

	Post findById(int postid);

	Post findPost(String title);

	void updatePost(Post post);

	void deleteById(int postid);

	boolean isPostUnique(Integer postid, String title);

}
