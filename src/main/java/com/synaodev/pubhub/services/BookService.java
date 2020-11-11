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
		books.removeIf((b) -> {
			return b.getPrice().doubleValue() >= price.doubleValue();
		});
		return books;
	}
	public List<Book> getBooksWithTag(Tag tag) {
		List<Book> books = repository.findAll();
		books.removeIf((b) -> {
			List<Tag> tags = b.getTags();
			for (Tag it : tags) {
				if (it.getId().longValue() == tag.getId().longValue()) {
					return false;
				}
			}
			return true;
		});
		return books;
	}
	public List<Book> getBooksWithTagName(String name) {
		List<Book> books = repository.findAll();
		books.removeIf((b) -> {
			List<Tag> tags = b.getTags();
			for (Tag it : tags) {
				String itName = it.getName();
				if (itName.compareTo(name) == 0) {
					return false;
				}
			}
			return true;
		});
		return books;
	}
	public List<Book> getBooksWithTagInList(List<Tag> query) {
		List<Book> books = repository.findAll();
		books.removeIf((b) -> {
			List<Tag> tags = b.getTags();
			for (Tag i : tags) {
				for (Tag j : query) {
					if (i.getId().longValue() == j.getId().longValue()) {
						return false;
					}
				}
			}
			return true;
		});
		return books;
	}
	public Book getBook(String isbn) {
		Optional<Book> optional = repository.findById(isbn);
		if (!optional.isPresent()) {
			return null;
		}
		return optional.get();
	}
	public Book addBook(Book book) {
		String isbn = book.getIsbn13();
		if (repository.existsById(isbn)) {
			return repository.findById(isbn).get();
		}
		return repository.save(book);
	}
	public Book updateBook(Book book) {
		String isbn = book.getIsbn13();
		if (!repository.existsById(isbn)) {
			return book;
		}
		return repository.save(book);
	}
	public boolean deleteBook(String isbn) {
		if (!repository.existsById(isbn)) {
			return false;
		}
		repository.deleteById(isbn);
		return true;
	}
}
