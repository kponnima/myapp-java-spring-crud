package com.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.PhotoDao;
import com.spring.model.Photo;

@Service("photoService")
@Transactional
public class PhotoServiceImpl implements PhotoService {

	@Autowired
	PhotoDao dao;

	public Photo findById(int imageid) {
		return dao.findById(imageid);
	}

	public List<Photo> findAll() {
		return dao.findAll();
	}

	public List<Photo> findAllByUserId(int userId) {
		return dao.findAllByUserId(userId);
	}

	public List<Photo> findAllByAlbumId(int albumId) {
		return dao.findAllByAlbumId(albumId);
	}

	public void savePhoto(Photo photo) {
		dao.save(photo);
	}

	public void deleteById(int imageid) {
		dao.deleteById(imageid);
	}

}
