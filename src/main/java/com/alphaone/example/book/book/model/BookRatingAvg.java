package com.alphaone.example.book.book.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "t_book_rate")
public class BookRatingAvg {

	@Id
	@Column(name = "book_id")
	private long bookId;

	@Column(name = "rate_cnt")
	private long ratingCnt;

	@Column(name = "rate_avg")
	private double avgRating;

	@OneToOne
	@MapsId
	@JoinColumn(name = "book_id")
	@JsonIgnore
	private Book book;

	protected BookRatingAvg() {
	}

	public BookRatingAvg(long bookId, long ratingCnt, double avgRating, Book book) {
		super();
		this.bookId = bookId;
		this.ratingCnt = ratingCnt;
		this.avgRating = avgRating;
		this.book = book;
	}

	public long getRatingCnt() {
		return ratingCnt;
	}

	public void setRatingCnt(long ratingCnt) {
		this.ratingCnt = ratingCnt;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public double getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}
}
