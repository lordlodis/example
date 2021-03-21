package com.alphaone.example.book.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alphaone.example.book.book.model.Book;
import com.alphaone.example.book.book.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping
	public List<Book> findAllBooks() {
		return bookService.findAll();
	}
	
	@GetMapping("/{bookId}")
	public Book findBook(@PathVariable Long bookId) {
		return bookService.findBook(bookId);
	}
}
