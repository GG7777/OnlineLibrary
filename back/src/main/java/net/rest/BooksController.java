package net.rest;

import net.model.Book;
import net.service.BooksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
@RequestMapping(value = "/api/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BooksController {
    private BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Book>> GetBooks(@RequestParam("start") @NotNull Integer start,
                                               @RequestParam("stop") @NotNull Integer stop) {
        List<Book> books = booksService.findByRange(start, stop);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Book> GetBook(@PathVariable("id") @NotNull Long bookId) {
        Book book = booksService.findById(bookId);

        if (book == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<Book> AddBook(@RequestBody @NotNull Book bookDto) {
        Book book = booksService.addNewBook(bookDto);

        if (book == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }
}
