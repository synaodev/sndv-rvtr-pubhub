package com.synaodev.pubhub.services;

import java.util.List;
import java.util.Optional;

import com.synaodev.pubhub.models.Book;
import com.synaodev.pubhub.models.Tag;
import com.synaodev.pubhub.repositories.BookRepository;

import org.springframework.stereotype.Service;

@Service
public class BookService {
	private final BookRepository repository;
	public BookService(BookRepository repository) {
		this.repository = repository;
	}
	public List<Book> allBooks() {
		return repository.findAll();
	}
	public List<Book> getBooksByTitle(String title) {
		return repository.findByTitle(title);
	}
	public List<Book> getBooksByAuthor(String author) {
		return repository.findByAuthor(author);
	}
	public List<Book> getBooksLessThanPrice(Double price) {
		List<Book> books = repository.findAll();
		books.removeIf((b) -> { return b.getPrice() >= price; });
		return books;
	}
	public List<Book> getBooksWithTag(Tag tag) {
		List<Book> books = repository.findAll();
		books.removeIf((b) -> {
			List<Tag> tags = b.getTags();
			for (Tag it : tags) {
				if (it.getId() == tag.getId()) {
					return false;
				}
			}
			return true;
		});
		return books;
	}
	public boolean doesTagExist(Tag tag) {
		List<Book> books = repository.findAll();
		for (Book book : books) {
			if (book.hasTag(tag)) {
				return true;
			}
		}
		return false;
	}
	public Optional<Book> getBook(String isbn) {
		return repository.findById(isbn);
	}
	public boolean addBook(Book book) {
		String isbn = book.getIsbn13();
		if (repository.existsById(isbn)) {
			return false;
		}
		book = repository.save(book);
		return true;
	}
	public boolean updateBook(Book book) {
		String isbn = book.getIsbn13();
		if (!repository.existsById(isbn)) {
			return false;
		}
		book = repository.save(book);
		return true;
	}
	public boolean deleteBook(String isbn) {
		if (!repository.existsById(isbn)) {
			return false;
		}
		repository.deleteById(isbn);
		return true;
	}
}
