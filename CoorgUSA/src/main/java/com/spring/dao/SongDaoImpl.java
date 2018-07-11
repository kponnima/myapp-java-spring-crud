package com.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.spring.model.Song;

@Repository("songDao")
public class SongDaoImpl extends AbstractDao<Integer, Song> implements SongDao {

	@SuppressWarnings("unchecked")
	public List<Song> findAll() {
		Criteria crit = createEntityCriteria();
		return (List<Song>) crit.list();
	}

	public void save(Song song) {
		persist(song);
	}

	public Song findById(int songid) {
		return getByKey(songid);
	}

	@SuppressWarnings("unchecked")
	public List<Song> findAllByUserId(int userId) {
		Criteria crit = createEntityCriteria();
		Criteria userCriteria = crit.createCriteria("user");
		userCriteria.add(Restrictions.eq("userid", userId));
		return (List<Song>) crit.list();
	}

	public void deleteById(int songid) {
		Song song = getByKey(songid);
		delete(song);
	}

}
