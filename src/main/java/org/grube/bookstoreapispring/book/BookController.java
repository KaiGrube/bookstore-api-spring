package org.grube.bookstoreapispring.book;

import org.grube.bookstoreapispring.error.ApiException;
import org.grube.bookstoreapispring.error.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://0.0.0.0:3001") // allow swagger
@RestController
@RequestMapping("/api/v2/")
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
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookService.readBooks(filter, limit, page, sortBy));
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable long id) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookService.readBookById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Book (id=%d) not found.", id))));
    }

    @PostMapping("/books")
    public ResponseEntity<Object> postBook(@Valid @RequestBody Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ApiException apiException = new ApiException(HttpStatus.BAD_REQUEST, "Validation Error");
            bindingResult.getAllErrors().forEach((error -> apiException.addSubMessage(error.getDefaultMessage())));
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(apiException);
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookService.createBook(book));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Long> deleteBook(@PathVariable long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(id);
    }
}