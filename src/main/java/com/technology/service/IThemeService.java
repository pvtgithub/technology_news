package com.technology.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.technology.dto.ThemeDTO;

public interface IThemeService {
	ThemeDTO save(ThemeDTO theme);
	void delete(long[] ids);
	List<ThemeDTO> themeList(Pageable pageAble);
	int countItemCategory();
	List<ThemeDTO> findThemeByCategory(long id);
}
