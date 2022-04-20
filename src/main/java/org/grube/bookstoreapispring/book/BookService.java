package org.grube.bookstoreapispring.book;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

//    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> readBooks() {
        Pageable wholePage = Pageable.unpaged();
        return bookRepository.findAll(wholePage).stream().toList();
    }

    public List<Book> readBooks(int page, int limit, String sortBy) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortBy));
        return bookRepository.findAll(pageable).stream().toList();
    }

    public Book createBook(Book book) {
        bookRepository.save(book);
        return book;
    }
}
