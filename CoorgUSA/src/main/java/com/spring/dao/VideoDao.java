package com.spring.dao;

import java.util.List;

import com.spring.model.Video;

public interface VideoDao {

	List<Video> findAll();

	Video findById(int videoid);

	List<Video> findAllByUserId(int userId);

	void save(Video video);

	void deleteById(int videoid);

}