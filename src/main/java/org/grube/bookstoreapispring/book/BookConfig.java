package org.grube.bookstoreapispring.book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BookConfig {

//    @Bean
//    CommandLineRunner commandLineRunner(BookRepository bookRepository) {
//        return args -> {
//         List<Book> books = List.of(
//                 new Book("Peter Pan"),
//                 new Book("Amalthea"),
//                 new Book("Moby Dick")
//         );
//         bookRepository.saveAll(books);
//        };
//    }
}
