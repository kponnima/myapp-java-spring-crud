package com.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.spring.model.Album;

@Repository("albumDao")
public class AlbumDaoImpl extends AbstractDao<Integer, Album> implements AlbumDao {

	static final Logger logger = LoggerFactory.getLogger(AlbumDaoImpl.class);

	@SuppressWarnings("unchecked")
	public List<Album> getAllAlbums() {
		Criteria crit = createEntityCriteria().addOrder(Order.asc("title"));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Album> album = (List<Album>) crit.list();
		return album;
	}

	public void save(Album album) {
		persist(album);
	}

	public Album findById(int albumid) {
		Album album = getByKey(albumid);
		if (album != null) {
			Hibernate.initialize(album.getAlbumid());
		}
		return album;
	}

	public Album findAlbum(String title) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("title", title));
		Album album = (Album) crit.uniqueResult();
		if (album != null) {
			Hibernate.initialize(album);
		}
		return album;
	}

	public void deleteById(int albumid) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("albumid", albumid));
		Album album = (Album) crit.uniqueResult();
		delete(album);
	}

}