package net.dto;

import lombok.Data;
import net.model.Author;
import net.model.Book;
import net.model.Genre;

import java.util.List;

@Data
public class BookDto extends BaseDto {
    private String name;
    private Integer pagesCount;
    private String avatar;
    private Integer publicationYear;
    private String shortDescription;
    private Integer rating;

    public static BookDto toDto(Book book) {
        BookDto bookDto = new BookDto();

        bookDto.setId(book.getId());
        bookDto.setCreated(book.getCreated());
        bookDto.setUpdated(book.getUpdated());

        bookDto.setName(book.getName());
        bookDto.setPagesCount(book.getPagesCount());
        bookDto.setAvatar(book.getAvatar());
        bookDto.setPublicationYear(book.getPublicationYear());
        bookDto.setShortDescription(book.getShortDescription());
        bookDto.setRating(book.getRating());

        return bookDto;
    }

    public static Book toBook(BookDto bookDto) {
        Book book = new Book();

        book.setId(bookDto.getId());
        book.setCreated(bookDto.getCreated());
        book.setUpdated(bookDto.getUpdated());

        book.setName(bookDto.getName());
        book.setPagesCount(bookDto.getPagesCount());
        book.setAvatar(bookDto.getAvatar());
        book.setPublicationYear(bookDto.getPublicationYear());
        book.setShortDescription(bookDto.getShortDescription());
        book.setRating(bookDto.getRating());

        return book;
    }
}