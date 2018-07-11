package com.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.AlbumDao;
import com.spring.model.Album;

@Service("albumService")
@Transactional
public class AlbumServiceImpl implements AlbumService {

	@Autowired
	private AlbumDao dao;

	public List<Album> getAllAlbums() {
		return dao.getAllAlbums();
	}

	public void save(Album album) {
		dao.save(album);
	}

	public Album findById(int albumid) {
		return dao.findById(albumid);
	}

	public Album findAlbum(String title) {
		Album album = dao.findAlbum(title);
		return album;
	}

	public void updateAlbum(Album album) {
		Album entity = dao.findById(album.getAlbumid());
		if (entity != null) {
			entity.setTitle(album.getTitle());
			entity.setDescription(album.getDescription());
			entity.setDate(album.getDate());
		}
	}

	public void deleteById(int albumid) {
		dao.deleteById(albumid);
	}

	public boolean isAlbumUnique(Integer albumid, String title) {
		Album album = findAlbum(title);
		return (album == null || ((albumid != null) && (album.getAlbumid() == albumid)));
	}

}
