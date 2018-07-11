package com.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.NewsDao;
import com.spring.model.News;

@Service("newsService")
@Transactional
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsDao dao;

	public List<News> getAllNews() {
		return dao.getAllNews();
	}

	public List<News> getNews(Integer offset, Integer maxResults) {
		return dao.getNews(offset, maxResults);
	}

	public Long count() {
		return dao.count();
	}

	public void save(News news) {
		dao.save(news);
	}

	public News findById(int newsid) {
		return dao.findById(newsid);
	}

	public News findNews(String title) {
		News news = dao.findNews(title);
		return news;
	}

	public void updateNews(News news) {
		News entity = dao.findById(news.getNewsid());
		if (entity != null) {
			entity.setTitle(news.getTitle());
			entity.setBody(news.getBody());
			entity.setDate(news.getDate());
		}
	}

	public void deleteById(int newsid) {
		dao.deleteById(newsid);
	}

	public boolean isNewsUnique(Integer newsid, String title) {
		News news = findNews(title);
		return (news == null || ((newsid != null) && (news.getNewsid() == newsid)));
	}

}
