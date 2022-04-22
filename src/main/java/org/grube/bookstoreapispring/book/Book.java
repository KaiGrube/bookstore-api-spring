package org.grube.bookstoreapispring.book;

import lombok.*;
import org.grube.bookstoreapispring.validation.Isbn13Constraint;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
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

    @NotNull (message = "subtitle must not be null")
    private String subtitle;

    @Isbn13Constraint // (message = "isbn is not a valid ISBN13")
    @NotNull (message = "isbn must not be null") // todo: proper validation for isbn13 (regex?)
    private String isbn13;

    @NotNull (message = "price must not be null")// todo: make price be of type double
    private double price;

    @URL (message = "image must be a valid url")
    @NotNull (message = "image must not be null")
    private String image;

    @URL (message = "url must be a valid url")
    @NotNull (message = "url must not be null") // todo: validation for url (regex?)
    private String url;

}
