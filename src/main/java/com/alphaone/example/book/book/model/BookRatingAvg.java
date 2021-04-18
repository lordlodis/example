package com.alphaone.example.book.book.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_book_rate")
public class BookRatingAvg {

	@Id
	private long id;

	@Column(name = "book_id")
	private long bookId;

	@Column(name = "avg_rate")
	private double avgRating;
	
	protected BookRatingAvg() {
	}

	public BookRatingAvg(long id, long bookId, double avgRating) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.avgRating = avgRating;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public double getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}
}
