package org.grube.bookstoreapispring.book;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<Book> readBooks() {
        Pageable wholePage = Pageable.unpaged();
        return bookRepository.findAll(wholePage)
                .stream()
                .toList();
    }

    public Optional<Book> readBookById(long id) {
        return bookRepository.findById(id);
    }


    public List<Book> readBooks(String filter, int limit, int page, String sortBy) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortBy));
        return bookRepository.findBooksByTitleContaining(filter, pageable)
                .stream()
                .toList();
    }
    public Book createBook(Book book) {
        bookRepository.save(book);
        return book;
    }
}
