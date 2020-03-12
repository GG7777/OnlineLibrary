package net.rest;

import net.dto.BookGenresAuthorsDto;
import net.model.Book;
import net.repository.AuthorsRepository;
import net.repository.GenresRepository;
import net.service.BooksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Stream;

@Controller
@RequestMapping(value = "/api/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BooksController {
    private BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping(value = "")
    public ResponseEntity<Stream<BookGenresAuthorsDto>> GetBooks(
            @RequestParam("start") @NotNull Integer start,
            @RequestParam("stop") @NotNull Integer count) {
        List<Book> books = booksService.findByRange(start, count);
        return new ResponseEntity<>(books.stream().map(book -> BookGenresAuthorsDto.toDto(book)), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<BookGenresAuthorsDto> GetBook(@PathVariable("id") @NotNull Long bookId) {
        Book book = booksService.findById(bookId);

        if (book == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(BookGenresAuthorsDto.toDto(book), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<BookGenresAuthorsDto> AddBook(@RequestBody @NotNull BookGenresAuthorsDto dto) {
        Book book = booksService.addNewBook(BookGenresAuthorsDto.toBook(dto));

        if (book == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(BookGenresAuthorsDto.toDto(book), HttpStatus.CREATED);
    }
}
