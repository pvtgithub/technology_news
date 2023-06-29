package com.technology.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "themes")
public class ThemeEntity extends BaseEntity{
	@Column
	private String name;
	
	@Column
	private String code;
	
	@OneToMany(mappedBy = "theme")
	List<NewsEntity> news = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private CategoryEntity category;
	
	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

	public List<NewsEntity> getNews() {
		return news;
	}

	public void setNews(List<NewsEntity> news) {
		this.news = news;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {	
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
