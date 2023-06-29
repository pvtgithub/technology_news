package com.technology.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "news")
public class NewsEntity extends BaseEntity {
	@Column(name = "title")
	private String title;

	@Column(name = "content",columnDefinition = "TEXT")
	private String content;
	
	@Column(name = "description",columnDefinition = "TEXT")
	private String description;
	
	@Column(name = "code")
	private String code;

	@Column(name = "view_count")
	private long viewCount;

	@ManyToOne
	@JoinColumn(name = "theme_id")
	private ThemeEntity theme;

	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ThemeEntity getTheme() {
		return theme;
	}

	public void setTheme(ThemeEntity theme) {
		this.theme = theme;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "news_tag", joinColumns = @JoinColumn(name = "news_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private List<TagEntity> listTags = new ArrayList<>();

	@OneToMany(mappedBy = "news")
	private List<CommentEntity> comments = new ArrayList<>();
	
	@OneToMany(mappedBy = "news")
	private List<ImageEntity> images = new ArrayList<>();

	
	
	public List<ImageEntity> getImages() {
		return images;
	}

	public void setImages(List<ImageEntity> images) {
		this.images = images;
	}

	public List<CommentEntity> getComments() {
		return comments;
	}

	public List<TagEntity> getListTags() {
		return listTags;
	}

	public void setListTags(List<TagEntity> listTags) {
		this.listTags = listTags;
	}

	public void setComments(List<CommentEntity> comments) {
		this.comments = comments;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public long getViewCount() {
		return viewCount;
	}

	public void setViewCount(long viewCount) {
		this.viewCount = viewCount;
	}

}
