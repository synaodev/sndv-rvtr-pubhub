package com.synaodev.pubhub.services;

import java.util.List;
import java.util.Optional;

import com.synaodev.pubhub.models.Tag;
import com.synaodev.pubhub.repositories.TagRepository;

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
	public Tag getTag(Long id) {
		Optional<Tag> optional = repository.findById(id);
		if (!optional.isPresent()) {
			return null;
		}
		return optional.get();
	}
	public Tag addTag(Tag tag) {
		List<Tag> tags = repository.findByName(tag.getName());
		if (!tags.isEmpty()) {
			return null;
		}
		return repository.save(tag);
	}
	public Tag updateTag(Tag tag) {
		Long id = tag.getId();
		if (!repository.existsById(id)) {
			return null;
		}
		return repository.save(tag);
	}
	public boolean deleteTag(Long id) {
		if (!repository.existsById(id)) {
			return false;
		}
		repository.deleteById(id);
		return true;
	}
}
