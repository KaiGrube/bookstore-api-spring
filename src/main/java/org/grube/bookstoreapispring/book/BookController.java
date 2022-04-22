package org.grube.bookstoreapispring.book;

import org.grube.bookstoreapispring.error.ApiException;
import org.grube.bookstoreapispring.error.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.net.URI;
import java.util.List;

//@CrossOrigin(origins = "http://0.0.0.0:3001") // allow swagger
@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks(
            @RequestParam(defaultValue = "", required = false)
            @Size(min = 0, max = 255)
            String filter,
            @RequestParam(defaultValue = "0", required = false)
            @Min(value = 0, message = "page must be equal to 0 or greater")
            Integer page,
            @RequestParam(defaultValue = "5", required = false)
            @Min(value = 1, message = "limit must be greater than zero")
            @Max(value = 100, message = "limit must be smaller than 100")
            int limit,
            @RequestParam(defaultValue="title", required = false)
            String sortBy
    ) {
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
    public ResponseEntity<Object> postBook(@Valid @RequestBody Book bookToCreate, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ApiException apiException = new ApiException(HttpStatus.BAD_REQUEST, "Validation Error");
            bindingResult.getAllErrors().forEach((error -> apiException.addSubMessage(error.getDefaultMessage())));
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(apiException);
        }
        Book newBook = bookService.createBook(bookToCreate);
        return ResponseEntity.created(URI.create("http://localhost:8080/books/" + newBook.getId()))
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookService.createBook(bookToCreate));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Long> deleteBook(@PathVariable long id) {
        bookService.readBookById(id).ifPresentOrElse(book -> bookService.deleteBookById(id), () -> {
            throw new ResourceNotFoundException("id not found");
        });

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(id);
    }
}