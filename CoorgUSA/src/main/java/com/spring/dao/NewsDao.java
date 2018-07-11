package com.spring.dao;

import java.util.List;

import com.spring.model.News;

public interface NewsDao {

	List<News> getAllNews();

	List<News> getNews(Integer offset, Integer maxResults);

	Long count();

	void save(News news);

	News findById(int newsid);

	News findNews(String title);

	void deleteById(int newsid);

}