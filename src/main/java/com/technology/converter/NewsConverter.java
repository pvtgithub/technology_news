package com.technology.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.technology.dto.NewsDTO;
import com.technology.entity.NewsEntity;
import com.technology.entity.TagEntity;
import com.technology.reponsitory.TagRepository;

@Component
public class NewsConverter {
	@Autowired
	private TagRepository tagRepository;
	
	public NewsEntity setListTagFromNewsEntity(NewsEntity entity,List<String> getTags) {
		List<TagEntity> listTags = new ArrayList<>();	// luu tru listTag neu khong tim thay trong database
		List<TagEntity> listTagsFinded = new ArrayList<>(); //luu tru listTag neu tim thay trong database
		for (String item : getTags) {
			if (tagRepository.findByName(item) != null) {	// neu tim thay
				TagEntity tag = tagRepository.findByName(item);	//lay lai tagEntity tìm được
				listTagsFinded.add(tag);	//thêm vào trong listTag
				entity.setListTags(listTagsFinded); // set lại listTag trong NewsEntity
			} else { 	//Ngược lại nếu không tìm thấy tag
				TagEntity tag = new TagEntity();  // thì thêm 1 tag mới
				tag.setName(item); // set name cho tag mới
				listTags.add(tag); // thêm tag đó vào trong listTag
				entity.setListTags(listTags); // set listTag cho NewsEntity
			}
		}
		return entity;
	}
	
	public NewsEntity toEntity(NewsDTO newsDTO,NewsEntity newsEntity) {
		
		newsEntity.setTitle(newsDTO.getTitle());
		newsEntity.setContent(newsDTO.getContent());
		newsEntity.setViewCount(newsDTO.getViewCount());
		
		return newsEntity;
	}
	
	public NewsDTO toDTO(NewsEntity newsEntity) {
		NewsDTO newsDTO = new NewsDTO();
		
		newsDTO.setId(newsEntity.getId());
		newsDTO.setTitle(newsEntity.getTitle());
		newsDTO.setContent(newsEntity.getContent());
		newsDTO.setViewCount(newsEntity.getViewCount());
		newsDTO.setThemeCode(newsEntity.getTheme().getCode());
		newsDTO.setCreatedBy(newsEntity.getCreatedBy());
		newsDTO.setCreatedDate(newsEntity.getCreatedDate());
		newsDTO.setModifiedBy(newsEntity.getModifiedBy());
		newsDTO.setModifiedDate(newsEntity.getModifiedDate());

		return newsDTO;
	}
}
