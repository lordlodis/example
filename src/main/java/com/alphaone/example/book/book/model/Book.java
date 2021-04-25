package com.alphaone.example.book.book.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "t_book")
public class Book {

	@Id
	@Column(name = "book_id")
	private long bookId;

	@Column(name = "title")
	private String title;

	@Column(name = "author")
	private String author;

	@OneToOne(mappedBy = "book", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private BookRatingAvg avgRating;

	protected Book() {
		// Auto-generated constructor stub used only in Hibernate
	}

	public Book(long id, String title, String author) {
		super();
		this.bookId = id;
		this.title = title;
		this.author = author;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
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

	public BookRatingAvg getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(BookRatingAvg avgRating) {
		this.avgRating = avgRating;
	}
}
