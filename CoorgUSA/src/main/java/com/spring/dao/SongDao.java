package com.spring.dao;

import java.util.List;

import com.spring.model.Song;

public interface SongDao {

	List<Song> findAll();

	Song findById(int songid);

	List<Song> findAllByUserId(int userId);

	void save(Song song);

	void deleteById(int songid);

}