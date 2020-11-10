package com.synaodev.pubhub.repositories;

import java.util.List;

import com.synaodev.pubhub.models.Book;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {
	public List<Book> findAll();
	public List<Book> findByTitle(String title);
	public List<Book> findByAuthor(String author);
}
