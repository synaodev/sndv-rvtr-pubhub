package com.synaodev.pubhub.services;

import java.util.List;
import java.util.Optional;

import com.synaodev.pubhub.models.Tag;
import com.synaodev.pubhub.models.TagRepository;

import org.springframework.stereotype.Service;

@Service
public class TagService {
	private final TagRepository repository;
	public TagService(TagRepository repository) {
		this.repository = repository;
	}
	public List<Tag> allTags() {
		return repository.findAll();
	}
	public List<Tag> getTagsByName(String name) {
		return repository.findByName(name);
	}
	public Optional<Tag> getTag(Long id) {
		return repository.findById(id);
	}
	public boolean addTag(Tag tag) {
		Long id = tag.getId();
		if (repository.existsById(id)) {
			return false;
		}
		tag = repository.save(tag);
		return true;
	}
	public boolean updateTag(Tag tag) {
		Long id = tag.getId();
		if (!repository.existsById(id)) {
			return false;
		}
		tag = repository.save(tag);
		return true;
	}
	public boolean deleteTag(Long id) {
		if (!repository.existsById(id)) {
			return false;
		}
		repository.deleteById(id);
		return true;
	}
}
