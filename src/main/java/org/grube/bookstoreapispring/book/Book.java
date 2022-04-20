package org.grube.bookstoreapispring.book;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
}
