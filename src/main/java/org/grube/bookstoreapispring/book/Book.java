package org.grube.bookstoreapispring.book;

import lombok.*;
import org.grube.bookstoreapispring.validation.Isbn13Constraint;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 1, message="title must be at least 1 character long")
    @NotNull (message = "title must not be null")
    private String title;

    @Size(min = 1, message="subtitle must be at least 1 character long")
    @NotNull (message = "subtitle must not be null")
    private String subtitle;

    @Isbn13Constraint // (message = "isbn is not a valid ISBN13") // custom validator
    @NotNull (message = "isbn must not be null")
    private String isbn13;

    @Min(value = 0, message = "price must be greater than or equal to 0")
    @NotNull (message = "price must not be null")
    private double price;

    @URL (message = "image must be a valid url")
    @NotNull (message = "image must not be null")
    private String image;

    @URL (message = "url must be a valid url")
    @NotNull (message = "url must not be null")
    private String url;

    public Book(String title, String subtitle, String isbn13, double price, String image, String url) {
        this.title = title;
        this.subtitle = subtitle;
        this.isbn13 = isbn13;
        this.price = price;
        this.image = image;
        this.url = url;
    }
}
