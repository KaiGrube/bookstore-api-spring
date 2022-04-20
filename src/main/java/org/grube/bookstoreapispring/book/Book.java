package org.grube.bookstoreapispring.book;

import lombok.*;

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
    @NotNull (message = "title must not be null")
    @Size(min = 1, message="title must be at least 1 character long")
    private String title;
    @NotNull (message = "subtitle must not be null")
    private String subtitle;
    @NotNull (message = "isbn must not be null") // todo: proper validation for isbn13 (regex?)
    private String isbn13;
    @NotNull (message = "price must not be null")// todo: make price be of type double
    private double price;
    @NotNull (message = "image must not be null")
    private String image;
    @NotNull (message = "url must not be null")// todo: validation for url (regex?)
    private String url;
}
