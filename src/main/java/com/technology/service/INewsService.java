package com.technology.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.technology.dto.NewsDTO;

public interface INewsService {
	NewsDTO save(NewsDTO newsDTO);
	void delete(long[] ids);
	List<NewsDTO> findAll(Pageable pageAble);
	int countItem();
	List<NewsDTO> getNewsByCategory(long id);
}
