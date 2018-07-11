package com.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.SongDao;
import com.spring.model.Song;

@Service("songService")
@Transactional
public class SongServiceImpl implements SongService {

	@Autowired
	SongDao dao;

	public Song findById(int songid) {
		return dao.findById(songid);
	}

	public List<Song> findAll() {
		return dao.findAll();
	}

	public List<Song> findAllByUserId(int userId) {
		return dao.findAllByUserId(userId);
	}

	public void saveSong(Song song) {
		dao.save(song);
	}

	public void deleteById(int songid) {
		dao.deleteById(songid);
	}

}
