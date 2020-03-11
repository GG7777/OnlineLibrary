package net.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "authors")
@Data
public class Author extends BaseEntity {
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @ManyToMany(cascade = CascadeType.REMOVE, mappedBy = "authors", fetch = FetchType.LAZY)
    private List<Book> books;
}
