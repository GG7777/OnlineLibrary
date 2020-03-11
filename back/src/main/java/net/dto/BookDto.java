package net.dto;

import lombok.Data;
import net.model.Author;
import net.model.Book;
import net.model.Genre;

import java.util.List;

@Data
public class BookDto {
    private String name;
    private Integer pagesCount;
    private String avatar;
    private Integer publicationYear;
    private String shortDescription;
    private Integer rating;
    private List<> authors;
    private List<> genres;

    public static BookDto toDto(Book book) {

    }

    public static Book toBook(BookDto bookDto) {

    }
}

//<dependency>
//<groupId>org.postgresql</groupId>
//<artifactId>postgresql</artifactId>
//<version>42.2.10</version>
//</dependency>
