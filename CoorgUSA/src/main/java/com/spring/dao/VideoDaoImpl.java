package com.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.spring.model.Video;

@Repository("videoDao")
public class VideoDaoImpl extends AbstractDao<Integer, Video> implements VideoDao {

	@SuppressWarnings("unchecked")
	public List<Video> findAll() {
		Criteria crit = createEntityCriteria();
		return (List<Video>) crit.list();
	}

	public void save(Video video) {
		persist(video);
	}

	public Video findById(int videoid) {
		return getByKey(videoid);
	}

	@SuppressWarnings("unchecked")
	public List<Video> findAllByUserId(int userId) {
		Criteria crit = createEntityCriteria();
		Criteria userCriteria = crit.createCriteria("user");
		userCriteria.add(Restrictions.eq("userid", userId));
		return (List<Video>) crit.list();
	}

	public void deleteById(int videoid) {
		Video video = getByKey(videoid);
		delete(video);
	}

}
