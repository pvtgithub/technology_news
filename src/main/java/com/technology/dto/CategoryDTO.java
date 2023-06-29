package com.technology.dto;

import java.util.ArrayList;
import java.util.List;

public class CategoryDTO extends BaseDTO{
	
	private String name;
	private String code;
	private List<ThemeDTO> listTheme= new ArrayList<>();
	
	
	public List<ThemeDTO> getListTheme() {
		return listTheme;
	}
	public void setListTheme(List<ThemeDTO> listTheme) {
		this.listTheme = listTheme;
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
