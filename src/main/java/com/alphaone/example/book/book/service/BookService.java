package com.alphaone.example.book.book.service;

import java.util.List;
import org.slf4j.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphaone.example.book.book.model.Book;
import com.alphaone.example.book.book.repository.BookRepository;

@Service
public class BookService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(BookService.class);

	@Autowired
	private BookRepository bookRepository;
	
	/**
	 * @return
	 */
	public List<Book> findAll() {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Loading all books from database");
		}
		return StreamSupport.stream(bookRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	}

	/**
	 * @param bookId
	 * @return
	 */
	public Book findBook(Long bookId) {
		return bookRepository.findById(bookId)
				.orElse(null);
	}

}
