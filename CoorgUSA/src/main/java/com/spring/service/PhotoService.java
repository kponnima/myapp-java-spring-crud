package com.spring.service;

import java.util.List;

import com.spring.model.Photo;

public interface PhotoService {

	Photo findById(int imageid);

	List<Photo> findAll();

	List<Photo> findAllByUserId(int imageid);

	List<Photo> findAllByAlbumId(int albumId);

	void savePhoto(Photo photo);

	void deleteById(int imageid);

}