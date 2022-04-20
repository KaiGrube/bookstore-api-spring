package org.grube.bookstoreapispring.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.grube.bookstoreapispring.error.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {

    ObjectMapper mapper = new ObjectMapper();
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> list = bookService.getBooks();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/books")
    public ResponseEntity<Object> postBook(@Valid @RequestBody Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ApiException apiException = new ApiException(HttpStatus.BAD_REQUEST, "Validation Error");
            bindingResult.getAllErrors().forEach((error -> apiException.addSubMessage(error.getDefaultMessage())));
            return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
        }
        Book newBook = bookService.createBook(book);
        return ResponseEntity.ok().body(newBook);
    }
}
