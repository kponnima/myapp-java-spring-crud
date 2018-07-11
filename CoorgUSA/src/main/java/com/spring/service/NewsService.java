package com.spring.service;

import java.util.List;

import com.spring.model.News;

public interface NewsService {

	List<News> getAllNews();

	List<News> getNews(Integer offset, Integer maxResults);

	Long count();

	void save(News news);

	News findById(int newsid);

	News findNews(String title);

	void updateNews(News news);

	void deleteById(int newsid);

	boolean isNewsUnique(Integer newsid, String title);

}
