package net.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "books")
@Data
public class Book extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "pages_count")
    private Integer pagesCount;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "publication_year")
    private Integer publicationYear;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Author> authors;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Genre> genres;
}

