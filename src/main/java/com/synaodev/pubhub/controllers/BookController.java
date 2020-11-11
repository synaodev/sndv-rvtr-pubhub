package com.synaodev.pubhub.controllers;

import java.util.List;

import javax.validation.Valid;

import com.synaodev.pubhub.models.Book;
import com.synaodev.pubhub.models.Tag;
import com.synaodev.pubhub.services.BookService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {
	private final BookService bookService;
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	@GetMapping("/book")
	public String index(Model model, @ModelAttribute("form-book") Book book) {
		List<Book> books = bookService.allBooks();
		model.addAttribute("books", books);
		return "index.jsp";
	}
	@PostMapping("/book")
	public String create(@Valid @ModelAttribute("form-book") Book book, BindingResult result) {
		if (!result.hasErrors()) {
			bookService.addBook(book);
		}
		return "redirect:/book";
	}
	@GetMapping("/book/{isbn13}")
	public String view(@PathVariable("isbn13") String isbn13, Model model, @ModelAttribute("form-tag") Tag tag) {
		Book book = bookService.getBook(isbn13);
		if (book == null) {
			return "redirect:/book";
		}
		model.addAttribute("book", book);
		model.addAttribute("tag", tag);
		return "book.jsp";
	}
}
