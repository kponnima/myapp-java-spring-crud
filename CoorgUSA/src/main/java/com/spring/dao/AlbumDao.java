package com.spring.dao;

import java.util.List;

import com.spring.model.Album;

public interface AlbumDao {

	List<Album> getAllAlbums();

	void save(Album album);

	Album findById(int albumid);

	Album findAlbum(String title);

	void deleteById(int albumid);

}
