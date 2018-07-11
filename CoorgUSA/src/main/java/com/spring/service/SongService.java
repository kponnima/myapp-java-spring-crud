package com.spring.service;

import java.util.List;

import com.spring.model.Song;

public interface SongService {

	Song findById(int songid);

	List<Song> findAll();

	List<Song> findAllByUserId(int userId);

	void saveSong(Song song);

	void deleteById(int songid);

}