package com.synaodev.pubhub.models;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {
	public List<Tag> findAll();
	public List<Tag> findByName(String name);
}
