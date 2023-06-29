package com.technology.api.output;

import com.technology.dto.CategoryDTO;
import java.util.*;
public class CategoryOutput {
	private int page;
	private int totalPage;
	private List<CategoryDTO> categoryList = new ArrayList<>();
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<CategoryDTO> getcategoryList() {
		return categoryList;
	}
	public void setcategoryList(List<CategoryDTO> categoryList) {
		this.categoryList = categoryList;
	}

}
