package com.synaodev.pubhub.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.synaodev.pubhub.models.Book;
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
	private final BookService service;
	public BookController(BookService service) {
		this.service = service;
	}
	@GetMapping("/book")
	public String index(Model model) {
		List<Book> books = service.allBooks();
		model.addAttribute("books", books);
		return "index.jsp";
	}
	@GetMapping("/book/{isbn}")
	public String view(@PathVariable("isbn") String isbn, Model model) {
		Optional<Book> optional = service.getBook(isbn);
		if (!optional.isPresent()) {
			return "redirect:/book";
		}
		Book book = optional.get();
		model.addAttribute("book", book);
		return "view.jsp";
	}
	@PostMapping("/api/book/post")
	public String create(@Valid @ModelAttribute("book") Book book, BindingResult result) {
		if (!result.hasErrors()) {
			service.addBook(book);
		}
		return "redirect:/book";
	}
}
