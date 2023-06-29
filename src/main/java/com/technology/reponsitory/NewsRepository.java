package com.technology.reponsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.technology.entity.NewsEntity;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
	
	@Transactional
	@Modifying
	@Query("DELETE FROM NewsTagEntity nt WHERE nt.news.id = :newsId")
	void deleteTagsByNewsId(@Param("newsId") Long newsId);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM ImageEntity img WHERE img.news.id = :newsId")
	void deleteImagesByNewsId(@Param("newsId") Long newsId);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM CommentEntity cmt WHERE cmt.news.id = :newsId")
	void deleteCommentsByNewsId(@Param("newsId") Long newsId);
	
	
	List<NewsEntity> findTop3ByTheme_Category_IdOrderByCreatedDateDesc(Long categoryId);
}