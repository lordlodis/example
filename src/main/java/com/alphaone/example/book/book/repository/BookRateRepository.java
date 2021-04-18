package com.alphaone.example.book.book.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.alphaone.example.book.book.model.BookRatingAvg;

@Repository
public interface BookRateRepository extends CrudRepository<BookRatingAvg, Long> {

}
