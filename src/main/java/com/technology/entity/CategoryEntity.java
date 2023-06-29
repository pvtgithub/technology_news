package com.technology.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity{
	
	@Column
	private String name;
	
	@Column
	private String code;
	
	@OneToMany(mappedBy = "category")
	List<ThemeEntity> themes = new ArrayList<>();

	public List<ThemeEntity> getThemes() {
		return themes;
	}

	public void setThemes(List<ThemeEntity> themes) {
		this.themes = themes;
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
