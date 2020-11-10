package com.synaodev.pubhub.repositories;

import java.util.List;

import com.synaodev.pubhub.models.Tag;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {
	public List<Tag> findAll();
	public List<Tag> findByName(String name);
}
