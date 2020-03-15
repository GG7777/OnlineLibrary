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
    private ImageStorageService imageStorageService;

    public BooksController(
            BooksService booksService,
            ImageStorageService imageStorageService) {
        this.booksService = booksService;
        this.imageStorageService = imageStorageService;
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

    @PostMapping(value = "")
    public ResponseEntity<BookGenresAuthorsDto> AddBook(
            @RequestBody BookGenresAuthorsDto dto,
            @RequestParam(value = "avatar", required = false) MultipartFile file) {
        Book book = booksService.addNewBook(BookGenresAuthorsDto.toBook(dto));
        if (book == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (file != null) {
            String avatarPath = imageStorageService.save(file);
            book.setAvatar(avatarPath);
        }

        return new ResponseEntity<>(BookGenresAuthorsDto.toDto(book), HttpStatus.CREATED);
    }

//    @PostMapping(value = "/file")
//    public ResponseEntity<String> saveFile(
//            @RequestParam("avatar") MultipartFile file) {
//        if (file != null)
//            return new ResponseEntity<>(imageStorageService.save(file), HttpStatus.OK);
//
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
}