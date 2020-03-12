package net.dto;

import lombok.Data;
import net.model.Author;

@Data
public class AuthorDto extends BaseDto {
    private String lastName;
    private String firstName;
    private String middleName;

    public static AuthorDto toDto(Author author) {
        AuthorDto authorDto = new AuthorDto();

        authorDto.setId(author.getId());
        authorDto.setCreated(author.getCreated());
        authorDto.setUpdated(author.getUpdated());

        authorDto.setLastName(author.getLastName());
        authorDto.setFirstName(author.getLastName());
        authorDto.setMiddleName(author.getMiddleName());

        return authorDto;
    }

    public static Author toAuthor(AuthorDto authorDto) {
        Author author = new Author();

        author.setId(authorDto.getId());
        author.setCreated(authorDto.getCreated());
        author.setUpdated(authorDto.getUpdated());

        author.setLastName(authorDto.getLastName());
        author.setFirstName(authorDto.getFirstName());
        author.setMiddleName(authorDto.getMiddleName());

        return author;
    }
}
