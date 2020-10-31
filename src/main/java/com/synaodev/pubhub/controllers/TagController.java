package com.synaodev.pubhub.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.synaodev.pubhub.models.Book;
import com.synaodev.pubhub.models.Tag;
import com.synaodev.pubhub.services.BookService;
import com.synaodev.pubhub.services.TagService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
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
		Optional<Tag> optional = tagService.getTag(id);
		if (!optional.isPresent()) {
			return "redirect:/";
		}
		Tag tag = optional.get();
		List<Book> books = bookService.getBooksWithTag(tag);
		model.addAttribute("tag", tag);
		model.addAttribute("books", books);
		return "tag.jsp";
	}
	@PostMapping("/api/tag/post/{isbn}")
	public String create(@PathVariable("isbn") String isbn, @Valid @ModelAttribute("tag") Tag tag, BindingResult result) {
		if (!result.hasErrors()) {
			tagService.addTag(tag);
			Book book = bookService.getBook(isbn).get();
			book.addTag(tag);
			bookService.updateBook(book);
		}
		return String.format("redirect:/book/%s", isbn);
	}
	@DeleteMapping("/api/tag/delete/{id}/{isbn}")
	public String remove(@PathVariable("id") Long id, @PathVariable("isbn") String isbn) {
		Tag tag = tagService.getTag(id).get();
		Book book = bookService.getBook(isbn).get();
		book.removeTag(tag);
		if (!bookService.doesTagExist(tag)) {
			tagService.deleteTag(id);
		}
		return String.format("redirect:/book/%s", isbn);
	}
}
