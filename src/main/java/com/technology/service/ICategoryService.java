package com.technology.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.technology.dto.CategoryDTO;

public interface ICategoryService {
	CategoryDTO save(CategoryDTO category);
	void delete(long[] ids);
	List<CategoryDTO> categoryList(Pageable pageAble);
	int countItemCategory();
	List<CategoryDTO> categoryAll();
}
