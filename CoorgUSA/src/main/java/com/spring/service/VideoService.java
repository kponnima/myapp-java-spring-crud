package com.spring.service;

import java.util.List;

import com.spring.model.Video;

public interface VideoService {

	Video findById(int videoid);

	List<Video> findAll();

	List<Video> findAllByUserId(int videoid);

	void saveVideo(Video video);

	void deleteById(int videoid);

}
