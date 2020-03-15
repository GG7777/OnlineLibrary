package net.service.Implementations;

import net.model.Book;
import net.repository.AuthorsRepository;
import net.repository.BooksRepository;
import net.repository.GenresRepository;
import net.service.BooksService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BooksServiceImpl implements BooksService {
    private BooksRepository booksRepo;
    private AuthorsRepository authorsRepo;
    private GenresRepository genresRepo;

    public BooksServiceImpl(BooksRepository booksRepo, AuthorsRepository authorsRepo, GenresRepository genresRepo) {
        this.booksRepo = booksRepo;
        this.authorsRepo = authorsRepo;
        this.genresRepo = genresRepo;
    }

    public Book findById(Long id) {
        Optional<Book> optionalBook = booksRepo.findById(id);
        return optionalBook.orElse(null);
    }

    public List<Book> findByRange(Integer start, Integer count) {
        List<Book> books = booksRepo.findAll();

        return books.subList(
                NormalizeValue(start, 0, books.size() - 1),
                NormalizeValue(start + count, 0, books.size()));
    }

    public Book addNewBook(Book book) {
        if (book.getId() != null)
            return null;

        book.setAuthors(book.getAuthors().stream().map(author -> authorsRepo.save(author)).collect(Collectors.toList()));
        book.setGenres(book.getGenres().stream().map(genre -> genresRepo.save(genre)).collect(Collectors.toList()));

        return booksRepo.save(book);
    }

    private Integer NormalizeValue(Integer value, Integer minValue, Integer maxValue) {
        return value < minValue ?
                minValue :
                value > maxValue ?
                        maxValue :
                        value;
    }
}