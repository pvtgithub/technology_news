package com.technology.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.technology.entity.TagEntity;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {
	TagEntity findByName(String name);
}
