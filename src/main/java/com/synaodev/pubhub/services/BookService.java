package com.synaodev.pubhub.services;

import java.util.List;
import java.util.Optional;

import com.synaodev.pubhub.models.Book;
import com.synaodev.pubhub.repositories.BookRepository;

import org.springframework.stereotype.Service;

@Service
public class BookService {
	private final BookRepository repository;
	public BookService(BookRepository repository) {
		this.repository = repository;
	}
	public List<Book> allBooks() {
		return repository.getBooks();
	}
	public List<Book> getBooksByTitle(String title) {
		return repository.getBooksByTitle(title);
	}
	public List<Book> getBooksByAuthor(String author) {
		return repository.getBooksByAuthor(author);
	}
	public List<Book> getBooksLessThanPrice(Double price) {
		return repository.getBooksLessThanPrice(price);
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