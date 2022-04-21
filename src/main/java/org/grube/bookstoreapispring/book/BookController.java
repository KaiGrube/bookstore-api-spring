package org.grube.bookstoreapispring.book;

import org.grube.bookstoreapispring.error.ApiException;
import org.grube.bookstoreapispring.error.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(@RequestParam String filter,
                                               @RequestParam int page,
                                               @RequestParam int limit,
                                               @RequestParam String sortBy) {
        return ResponseEntity
                .ok()
                .body(bookService.readBooks(filter, limit, page, sortBy));
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable long id) {
        return ResponseEntity
                .ok()
                .body(bookService.readBookById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Book not found. id=%d", id))));
    }

    @PostMapping("/books")
    public ResponseEntity<Object> postBook(@Valid @RequestBody Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ApiException apiException = new ApiException(HttpStatus.BAD_REQUEST, "Validation Error");
            bindingResult.getAllErrors().forEach((error -> apiException.addSubMessage(error.getDefaultMessage())));
            return ResponseEntity
                    .badRequest()
                    .body(apiException);
        }
        return ResponseEntity.ok().body(bookService.createBook(book));
    }
}