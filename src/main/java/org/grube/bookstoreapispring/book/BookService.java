package org.grube.bookstoreapispring.book;

import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

//    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book createBook(Book book) {
        bookRepository.save(book);
        return book;
    }
}
