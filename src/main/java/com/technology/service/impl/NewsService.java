package com.technology.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.technology.dto.NewsDTO;
import com.technology.entity.ImageEntity;
import com.technology.entity.NewsEntity;
import com.technology.entity.TagEntity;
import com.technology.entity.ThemeEntity;
import com.technology.reponsitory.CategoryRepository;
import com.technology.reponsitory.ImageRepository;
import com.technology.reponsitory.NewsRepository;
import com.technology.reponsitory.TagRepository;
import com.technology.reponsitory.ThemeRepository;
import com.technology.service.INewsService;

@Service
public class NewsService implements INewsService {

	@Autowired
	private NewsRepository newsRepository; // KHởi tạo 1 repository để sử dụng các phương thức truy vấn có sẵn của
											// JpaRepository

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ThemeRepository themeRepository;
	
	@Autowired
	private TagRepository tagRepository;

	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private ModelMapper modelMapper; // dùng thư viện ModelMapper để conver Entity sang DTO và ngược lại

	@Override
	public NewsDTO save(NewsDTO newsDTO) {
		NewsEntity newsEntity = new NewsEntity(); // tạo mới 1 entity
		if (newsDTO.getId() != null) { // -----SỬA BÀI VIẾT--------
			NewsEntity oldNewsEntity = newsRepository.findOne(newsDTO.getId());
			modelMapper.map(newsDTO, oldNewsEntity);
			
			List<TagEntity> listTags = new ArrayList<>();	// luu tru listTag neu khong tim thay trong database
			for (String item : newsDTO.getTags()) {
				if (tagRepository.findByName(item) != null) {	// neu tim thay
					TagEntity tag = tagRepository.findByName(item);	//lay lai tagEntity tìm được
					listTags.add(tag);	//thêm vào trong listTag
				} else { 	//Ngược lại nếu không tìm thấy tag
					TagEntity tag = new TagEntity();  // thì thêm 1 tag mới
					tag.setName(item); // set name cho tag mới
					listTags.add(tag); // thêm tag đó vào trong listTag
				}
				oldNewsEntity.setListTags(listTags); // set listTag cho NewsEntity
			}
			
			newsEntity = oldNewsEntity;
		} else { // -----------THÊM BÀI VIẾT------------
			newsEntity = modelMapper.map(newsDTO, NewsEntity.class); // cover DTO sang entity
			List<TagEntity> listTags = new ArrayList<>();	// luu tru listTag neu khong tim thay trong database
			List<TagEntity> listTagsFinded = new ArrayList<>(); //luu tru listTag neu tim thay trong database
			for (String item : newsDTO.getTags()) {
				if (tagRepository.findByName(item) != null) {	// neu tim thay
					TagEntity tag = tagRepository.findByName(item);	//lay lai tagEntity tìm được
					listTagsFinded.add(tag);	//thêm vào trong listTag
					newsEntity.setListTags(listTagsFinded); // set lại listTag trong NewsEntity
				} else { 	//Ngược lại nếu không tìm thấy tag
					TagEntity tag = new TagEntity();  // thì thêm 1 tag mới
					tag.setName(item); // set name cho tag mới
					listTags.add(tag); // thêm tag đó vào trong listTag
					newsEntity.setListTags(listTags); // set listTag cho NewsEntity
				}
			}
		}
		ThemeEntity theme = themeRepository.findOneByCode(newsDTO.getThemeCode()); // Tìm category bằng
																								// categoryCode của
																								// newsDTO bằng thư viện
																								// dược gọi trong
																								// categoryRepository
		newsEntity.setTheme(theme); // đặt category_id thông qua category được tìm thấy
		newsEntity = newsRepository.save(newsEntity); // gọi hàm truy vấn có sẵn để thêm thông tin trong JpaRepository
		return modelMapper.map(newsEntity, NewsDTO.class); // cover sang Dto và trả về thông tin cho API
	}

	@Override
	public void delete(long[] ids) {
		for (long id : ids) {
			newsRepository.deleteTagsByNewsId(id);
			newsRepository.deleteImagesByNewsId(id);
			newsRepository.deleteCommentsByNewsId(id);
			newsRepository.delete(id);
		}
	}

	@Override
	public List<NewsDTO> findAll(Pageable pageAble) { // Phân trang
		List<NewsDTO> newsList = new ArrayList<>();
		List<NewsEntity> entities = newsRepository.findAll(pageAble).getContent();
		for (NewsEntity item : entities) {
			NewsDTO dto = modelMapper.map(item, NewsDTO.class);
			newsList.add(dto);
		}
		return newsList;
	}

	@Override
	public int countItem() {
		return (int) newsRepository.count();
	}

	@Override
	public List<NewsDTO> getNewsByCategory(long id) {
		List<NewsEntity> newsEntity = newsRepository.findTop3ByTheme_Category_IdOrderByCreatedDateDesc(id);
		List<NewsDTO> newsDTO = new ArrayList<>();
		for(NewsEntity news : newsEntity) {
			List<ImageEntity> imgEntity = imageRepository.findByNewsId(news.getId());
			news.setImages(imgEntity);
			NewsDTO dto = modelMapper.map(news, NewsDTO.class);
			newsDTO.add(dto);
		}
		return newsDTO;
	}

}
