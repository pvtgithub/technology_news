package com.technology.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.technology.dto.ThemeDTO;
import com.technology.entity.CategoryEntity;
import com.technology.entity.ThemeEntity;
import com.technology.reponsitory.CategoryRepository;
import com.technology.reponsitory.ThemeRepository;
import com.technology.service.IThemeService;

@Service
public class ThemeService implements IThemeService{
	@Autowired
	private ThemeRepository themeRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ThemeDTO save(ThemeDTO theme) {
		ThemeEntity themeEntity = new ThemeEntity();
		if (theme.getId() != null) {
			ThemeEntity oldTheme = themeRepository.findOne(theme.getId());
			modelMapper.map(theme, oldTheme);
			themeEntity = oldTheme;
		} else {
			themeEntity = modelMapper.map(theme, ThemeEntity.class);
		}
		CategoryEntity category = categoryRepository.findOneByCode(theme.getCategoryCode());
		themeEntity.setCategory(category);
		themeEntity = themeRepository.save(themeEntity);
		return modelMapper.map(themeEntity, ThemeDTO.class);
	}

	@Override
	public void delete(long[] ids) {
		for(long id : ids) {
			themeRepository.delete(id);
		}
	}

	@Override
	public List<ThemeDTO> themeList(Pageable pageAble) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countItemCategory() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ThemeDTO> findThemeByCategory(long id) {
		List<ThemeDTO> listTheme = new ArrayList<>();
		List<ThemeEntity> listThemeEntity = new ArrayList<>();
		CategoryEntity category = categoryRepository.findOne(id);
		listThemeEntity = category.getThemes();
		for(ThemeEntity theme : listThemeEntity) {
			ThemeDTO themeDTO = modelMapper.map(theme, ThemeDTO.class);
			listTheme.add(themeDTO);
		}
		return listTheme;
	}

}
