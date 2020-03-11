package net.service.Implementations;

import net.model.Book;
import net.repository.BooksRepository;
import net.service.BooksService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksServiceImpl implements BooksService {
    private BooksRepository booksRepo;

    public BooksServiceImpl(BooksRepository booksRepo) {
        this.booksRepo = booksRepo;
    }

    public Book findById(Long id) {
        Optional<Book> optionalBook = booksRepo.findById(id);
        return optionalBook.orElse(null);
    }

    public List<Book> findByRange(Integer start, Integer stop) {
        List<Book> books = booksRepo.findAll();

        return books.subList(
                NormalizeValue(start, 0, books.size() - 1),
                NormalizeValue(stop + 1, 0, books.size()));
    }

    public Book addNewBook(Book book) {
        if (book.getId() != null)
            return null;
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
