package com.synaodev.pubhub.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="books")
public class Book {
	@Id
	@NotEmpty
	@Size(min = 13, max = 13)
	private String isbn13;
	@NotEmpty
	@Size(max = 100)
	private String title;
	@NotEmpty
	@Size(max = 80)
	private String author;
	@NotNull
	private Date publishDate;
	@NotNull
	@Digits(integer = 3, fraction = 2)
	private Double price;
	@Lob
	@Column(name = "content", columnDefinition = "BLOB")
	private Byte[] content;
	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Tag> tags;
	public Book(String isbn13, String title, String author, Byte[] content) {
		this.isbn13 = isbn13;
		this.title = title;
		this.author = author;
		this.publishDate = new Date();
		this.content = content;
	}
	public Book(String isbn13, String title, String author, Date publishDate, Byte[] content) {
		this.isbn13 = isbn13;
		this.title = title;
		this.author = author;
		this.publishDate = publishDate;
		this.content = content;
	}
	public Book() {
		this.isbn13 = null;
		this.title = null;
		this.author = null;
		this.publishDate = new Date();
		this.content = null;
	}
	public String getIsbn13() {
		return isbn13;
	}
	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Byte[] getContent() {
		return content;
	}
	public void setContent(Byte[] content) {
		this.content = content;
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void addTag(Tag tag) {
		if (tags != null) {
			boolean shouldAdd = true;
			for (Tag it : tags) {
				if (it.getId() == tag.getId()) {
					shouldAdd = false;
					break;
				}
			}
			if (shouldAdd) {
				tags.add(tag);
			}
		}
	}
	public void removeTag(Tag tag) {
		if (tags != null) {
			tags.remove(tag);
		}
	}
	public boolean hasTag(Tag tag) {
		if (tags != null) {
			for (Tag it : tags) {
				if (it.getId() == tag.getId()) {
					return true;
				}
			}
		}
		return false;
	}
}
