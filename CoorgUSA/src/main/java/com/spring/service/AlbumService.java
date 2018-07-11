package com.spring.service;

import java.util.List;

import com.spring.model.Album;

public interface AlbumService {

	List<Album> getAllAlbums();

	void save(Album album);

	Album findById(int albumid);

	Album findAlbum(String title);

	void deleteById(int albumid);

}
