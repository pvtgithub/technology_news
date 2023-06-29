package com.technology.api.output;

import java.util.*;

import com.technology.dto.NewsDTO;

public class NewsOutput {
	private int page;
	private int totalPage;
	private List<NewsDTO> listNews = new ArrayList<>();
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
	public List<NewsDTO> getListNews() {
		return listNews;
	}
	public void setListNews(List<NewsDTO> listNews) {
		this.listNews = listNews;
	};
}
