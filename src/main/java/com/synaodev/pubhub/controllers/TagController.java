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

@Controller
public class TagController {
	private final BookService bookService;
	private final TagService tagService;
	public TagController(BookService bookService, TagService tagService) {
		this.bookService = bookService;
		this.tagService = tagService;
	}
	@GetMapping("/tag/{id}")
	public String view(@PathVariable("id") Long id, Model model) {
		Tag tag = tagService.getTag(id);
		if (tag == null) {
			return "redirect:/";
		}
		List<Book> books = bookService.getBooksWithTagName(tag.getName());
		if (books.isEmpty()) {
			return "redirect:/";
		}
		model.addAttribute("tag", tag);
		model.addAttribute("books", books);
		return "tag.jsp";
	}
	@PostMapping("/tag/{isbn13}")
	public String create(@PathVariable("isbn13") String isbn13, @Valid @ModelAttribute("form-tag") Tag tag, BindingResult result) {
		if (!result.hasErrors()) {
			Book book = bookService.getBook(isbn13);
			if (book == null) {
				return "redirect:/";
			}
			tag = tagService.addTag(tag);
			book.addTag(tag);
			book = bookService.updateBook(book);
		}
		return String.format("redirect:/book/%s", isbn13);
	}
	@PostMapping("/tag/{id}/{isbn13}")
	public String remove(@PathVariable("id") Long id, @PathVariable("isbn13") String isbn13) {
		Tag tag = tagService.getTag(id);
		if (tag == null) {
			return "redirect:/";
		}
		Book book = bookService.getBook(isbn13);
		if (book == null) {
			return "redirect:/";
		}
		book.removeTag(tag);
		book = bookService.updateBook(book);
		List<Book> booksWithTag = bookService.getBooksWithTag(tag);
		if (booksWithTag.isEmpty()) {
			tagService.deleteTag(id);
		}
		return String.format("redirect:/book/%s", isbn13);
	}
}
