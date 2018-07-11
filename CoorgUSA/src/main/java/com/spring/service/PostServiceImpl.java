package com.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.PostDao;
import com.spring.model.Post;

@Service("postService")
@Transactional
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDao dao;

	public List<Post> getAllPosts() {
		return dao.getAllPosts();
	}

	public List<Post> getPosts(Integer offset, Integer maxResults) {
		return dao.getPosts(offset, maxResults);
	}

	public Long count() {
		return dao.count();
	}

	public void save(Post post) {
		dao.save(post);
	}

	public Post findById(int id) {
		return dao.findById(id);
	}

	public Post findPost(String title) {
		Post post = dao.findPost(title);
		return post;
	}

	public void updatePost(Post post) {
		Post entity = dao.findById(post.getPostid());
		if (entity != null) {
			entity.setTitle(post.getTitle());
			entity.setBody(post.getBody());
			entity.setDate(post.getDate());
		}
	}

	public void deleteById(int id) {
		dao.deleteById(id);
	}

	public boolean isPostUnique(Integer postid, String title) {
		Post post = findPost(title);
		return (post == null || ((postid != null) && (post.getPostid() == postid)));
	}

}
