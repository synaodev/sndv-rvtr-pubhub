package com.synaodev.pubhub.controllers;

import java.util.List;

import javax.validation.Valid;

import com.synaodev.pubhub.models.Book;
import com.synaodev.pubhub.models.Tag;
import com.synaodev.pubhub.services.BookService;
import com.synaodev.pubhub.services.TagService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {
	private final BookService bookService;
	private final TagService tagService;
	public BookController(BookService bookService, TagService tagService) {
		this.bookService = bookService;
		this.tagService = tagService;
	}
	@GetMapping("/book")
	public String find(@RequestParam String query, Model model) {
		List<Tag> tags = tagService.getTagsLike(query);
		List<Book> results = bookService.getBooksWithTagInList(tags);
		model.addAttribute("query", query);
		model.addAttribute("results", results);
		return "find.jsp";
	}
	@PostMapping("/book")
	public String create(@Valid @ModelAttribute("form-book") Book book, BindingResult result) {
		if (!result.hasErrors()) {
			bookService.addBook(book);
		}
		return "redirect:/";
	}
	@GetMapping("/book/{isbn13}")
	public String view(@PathVariable("isbn13") String isbn13, Model model, @ModelAttribute("form-tag") Tag tag) {
		Book book = bookService.getBook(isbn13);
		if (book == null) {
			return "redirect:/";
		}
		model.addAttribute("book", book);
		model.addAttribute("tag", tag);
		return "book.jsp";
	}
}
