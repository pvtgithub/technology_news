package com.technology.entity;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tags")
public class TagEntity extends BaseEntity {
	@Column
	private String name;

	@ManyToMany(mappedBy = "listTags")
	private List<NewsEntity> listNews = new ArrayList<>();

	public List<NewsEntity> getListNews() {
		return listNews;
	}

	public void setListNews(List<NewsEntity> listNews) {
		this.listNews = listNews;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
