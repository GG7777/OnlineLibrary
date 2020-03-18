package net.rest.admin;

import net.dto.BookGenresAuthorsDto;
import net.model.Book;
import net.service.BooksService;
import net.service.ImageStorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/api/admin/books")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminBooksController {

    private BooksService booksService;
    private ImageStorageService imageStorageService;

    public AdminBooksController(
            BooksService booksService,
            ImageStorageService imageStorageService) {
        this.booksService = booksService;
        this.imageStorageService = imageStorageService;
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
