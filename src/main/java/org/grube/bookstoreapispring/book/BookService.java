package org.grube.bookstoreapispring.book;

import org.springframework.data.domain.Page;
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
    public Optional<Book> readBookById(long id) {
        return bookRepository.findById(id);
    }

    public List<Book> readAllBooks() {
        Pageable wholePage = Pageable.unpaged();
        return bookRepository.findAll(wholePage)
                .stream()
                .toList();
    }
    public BookSearchResults searchBooks(String titleFilter, int pageLimit, int pageNumber, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageLimit, Sort.by(sortBy));
        Page<Book> page = bookRepository.findBooksByTitleContainingIgnoreCase(titleFilter, pageable);
        return new BookSearchResults(
                titleFilter,
                pageLimit,
                page.getTotalElements(),
                page.getTotalPages(),
                pageable.getPageNumber(),
                page.stream().toList());
    }
    public Book createBook(Book book) {
        bookRepository.save(book);
        return book;
    }

    public void deleteBookById(long id) {
        bookRepository.deleteById(id);
    }
}
