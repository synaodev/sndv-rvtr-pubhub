package com.synaodev.pubhub.models;

import javax.persistence.Entity;
// import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tags")
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	@Size(min = 1, max = 100)
	private String name;
	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "book_isbn13")
	// private Book book;
	public Tag(String name) {
		this.name = name;
		// this.book = null;
	}
	public Tag() {
		this.name = null;
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	// public Book getBook() {
		// return book;
	// }
	// public void setBook(Book book) {
		// this.book = book;
	// }
}
