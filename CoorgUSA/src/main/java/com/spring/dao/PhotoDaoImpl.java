package com.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.spring.model.Photo;

@Repository("photoDao")
public class PhotoDaoImpl extends AbstractDao<Integer, Photo> implements PhotoDao {

	@SuppressWarnings("unchecked")
	public List<Photo> findAll() {
		Criteria crit = createEntityCriteria();
		return (List<Photo>) crit.list();
	}

	public void save(Photo photo) {
		persist(photo);
	}

	public Photo findById(int imageid) {
		return getByKey(imageid);
	}

	@SuppressWarnings("unchecked")
	public List<Photo> findAllByUserId(int userId) {
		Criteria crit = createEntityCriteria();
		Criteria userCriteria = crit.createCriteria("user");
		userCriteria.add(Restrictions.eq("userid", userId));
		return (List<Photo>) crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<Photo> findAllByAlbumId(int albumId) {
		Criteria crit = createEntityCriteria();
		Criteria albumCriteria = crit.createCriteria("album");
		albumCriteria.add(Restrictions.eq("albumid", albumId));
		return (List<Photo>) crit.list();
	}

	public void deleteById(int imageid) {
		Photo photo = getByKey(imageid);
		delete(photo);
	}

}
