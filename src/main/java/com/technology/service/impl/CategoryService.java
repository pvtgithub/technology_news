package com.technology.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.technology.dto.CategoryDTO;
import com.technology.dto.ThemeDTO;
import com.technology.entity.CategoryEntity;
import com.technology.entity.ThemeEntity;
import com.technology.reponsitory.CategoryRepository;
import com.technology.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDTO save(CategoryDTO categoryDTO) {
		CategoryEntity categoryEntity = new CategoryEntity();
		if (categoryDTO.getId() != null) {
			CategoryEntity oldCategory = categoryRepository.findOne(categoryDTO.getId());
			modelMapper.map(categoryDTO, oldCategory);
			categoryEntity = oldCategory;
		} else {
			categoryEntity = modelMapper.map(categoryDTO, CategoryEntity.class);
		}
		categoryEntity = categoryRepository.save(categoryEntity);
		return modelMapper.map(categoryEntity, CategoryDTO.class);
	}

	@Override
	public void delete(long[] ids) {
		for (long id : ids) {
			categoryRepository.delete(id);
		}
	}

	@Override
	public List<CategoryDTO> categoryList(Pageable pageAble) {
		List<CategoryDTO> categoryListDTO = new ArrayList<>();
		List<CategoryEntity> categoryListEntities = new ArrayList<>();
		categoryListEntities = categoryRepository.findAll(pageAble).getContent();
		for(CategoryEntity item: categoryListEntities) {
			CategoryDTO dto = modelMapper.map(item,CategoryDTO.class);
			categoryListDTO.add(dto);
		}
		return categoryListDTO;
	}
	
	@Override
	public List<CategoryDTO> categoryAll(){
		List<CategoryDTO> categoryListDTO = new ArrayList<>();
		List<CategoryEntity> categoryListEntity = new ArrayList<>();
		categoryListEntity = categoryRepository.findAll();
		for(CategoryEntity item: categoryListEntity) {
			List<ThemeDTO> listTheme = new ArrayList<>();
			List<ThemeEntity> listThemeEntity = new ArrayList<>();
			listThemeEntity = item.getThemes();
			for(ThemeEntity i: listThemeEntity) {
				ThemeDTO tDTO = modelMapper.map(i, ThemeDTO.class);
				listTheme.add(tDTO);
			}
			CategoryDTO dto = modelMapper.map(item,CategoryDTO.class);
			dto.setListTheme(listTheme);
			categoryListDTO.add(dto);
		}
		return categoryListDTO;
	}

	@Override
	public int countItemCategory() {
		return (int) categoryRepository.count();
	}

}
