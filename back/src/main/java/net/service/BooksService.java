package net.service;

import net.model.Book;

import java.util.List;

public interface BooksService {
    Book findById(Long id);
    List<Book> findByRange(Integer start, Integer stop);
    Book addNewBook(Book book);
}
