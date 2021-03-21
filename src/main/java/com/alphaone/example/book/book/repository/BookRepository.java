package com.alphaone.example.book.book.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alphaone.example.book.book.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

}
