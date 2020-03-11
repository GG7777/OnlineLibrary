package net.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "genres")
@Data
public class Genre extends BaseEntity {
    @Column(name = "genre")
    private String genre;

    @ManyToMany(cascade = CascadeType.REMOVE, mappedBy = "genres", fetch = FetchType.LAZY)
    private List<Book> books;
}
