package com.spring.dao;

import java.util.List;

import com.spring.model.Photo;

public interface PhotoDao {

	List<Photo> findAll();

	Photo findById(int imageid);

	List<Photo> findAllByUserId(int userId);

	List<Photo> findAllByAlbumId(int albumId);

	void save(Photo photo);

	void deleteById(int imageid);

}