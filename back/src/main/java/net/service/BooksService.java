package net.service;

import net.model.Book;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BooksService {
    Book findById(Long id);
    List<Book> findByRange(Integer start, Integer count);
    Book addNewBook(Book book);
}
