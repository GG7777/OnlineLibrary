package net.dto;

import lombok.Data;
import net.model.Genre;

@Data
public class GenreDto extends BaseDto {
    private String genre;

    public static GenreDto toDto(Genre genre) {
        GenreDto genreDto = new GenreDto();

        genreDto.setId(genre.getId());
        genreDto.setCreated(genre.getCreated());
        genreDto.setUpdated(genre.getUpdated());

        genreDto.setGenre(genre.getGenre());

        return genreDto;
    }

    public static Genre toGenre(GenreDto genreDto) {
        Genre genre = new Genre();

        genre.setId(genreDto.getId());
        genre.setCreated(genreDto.getCreated());
        genre.setUpdated(genreDto.getUpdated());

        genre.setGenre(genreDto.getGenre());

        return genre;
    }
}
