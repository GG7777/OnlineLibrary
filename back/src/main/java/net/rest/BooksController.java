package net.rest;

import net.dto.BookGenresAuthorsDto;
import net.model.Book;
import net.service.BooksService;
import net.service.ImageStorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Stream;

@Controller
@RequestMapping(value = "/api/books")
public class BooksController {
    private BooksService booksService;

    public BooksController(
            BooksService booksService,
            ImageStorageService imageStorageService) {
        this.booksService = booksService;
    }

    @GetMapping(value = "")
    public ResponseEntity<Stream<BookGenresAuthorsDto>> GetBooks(
            @RequestParam("start") Integer start,
            @RequestParam("count") Integer count) {
        List<Book> books = booksService.findByRange(start, count);
        return new ResponseEntity<>(books.stream().map(book -> BookGenresAuthorsDto.toDto(book)), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookGenresAuthorsDto> GetBook(@PathVariable("id") Long bookId) {
        Book book = booksService.findById(bookId);

        if (book == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(BookGenresAuthorsDto.toDto(book), HttpStatus.OK);
    }
}