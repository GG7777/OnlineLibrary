package net.dto;

import lombok.Data;
import net.model.Book;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class BookGenresAuthorsDto {
    private BookDto book;
    private List<GenreDto> genres;
    private List<AuthorDto> authors;

    public static BookGenresAuthorsDto toDto(Book book) {
        BookGenresAuthorsDto dto = new BookGenresAuthorsDto();

        dto.setBook(BookDto.toDto(book));
        dto.setGenres(book.getGenres().stream().map(genre -> GenreDto.toDto(genre)).collect(Collectors.toList()));
        dto.setAuthors(book.getAuthors().stream().map(author -> AuthorDto.toDto(author)).collect(Collectors.toList()));

        return dto;
    }

    public static Book toBook(BookGenresAuthorsDto dto) {
        Book book = BookDto.toBook(dto.getBook());

        book.setGenres(dto.getGenres().stream().map(genreDto -> GenreDto.toGenre(genreDto)).collect(Collectors.toList()));
        book.setAuthors(dto.getAuthors().stream().map(authorDto -> AuthorDto.toAuthor(authorDto)).collect(Collectors.toList()));

        return book;
    }
}
