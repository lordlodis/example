package com.alphaone.example.book.book.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alphaone.example.book.book.model.Book;
import com.alphaone.example.book.book.service.BookService;

@Controller
@RequestMapping("/books")
public class BookWebController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping
	public String getBooks(Model model) {
		List<Book> books = bookService.findAll();
		model.addAttribute("books", books);
		return "books";
	}	
}
