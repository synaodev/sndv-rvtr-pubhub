package com.synaodev.pubhub.controllers;

import java.util.List;

import com.synaodev.pubhub.models.Book;
import com.synaodev.pubhub.services.BookService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class IndexController {
	private final BookService bookService;
	public IndexController(BookService bookService) {
		this.bookService = bookService;
	}
	@GetMapping("/")
	public String index(Model model, @ModelAttribute("form-book") Book book) {
		List<Book> books = bookService.allBooks();
		model.addAttribute("books", books);
		return "index.jsp";
	}
}
