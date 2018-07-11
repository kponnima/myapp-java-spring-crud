package com.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.VideoDao;
import com.spring.model.Video;

@Service("videoService")
@Transactional
public class VideoServiceImpl implements VideoService {

	@Autowired
	VideoDao dao;

	public Video findById(int videoid) {
		return dao.findById(videoid);
	}

	public List<Video> findAll() {
		return dao.findAll();
	}

	public List<Video> findAllByUserId(int userId) {
		return dao.findAllByUserId(userId);
	}

	public void saveVideo(Video video) {
		dao.save(video);
	}

	public void deleteById(int videoid) {
		dao.deleteById(videoid);
	}

}
