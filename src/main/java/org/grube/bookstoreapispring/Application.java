package org.grube.bookstoreapispring;

import org.grube.bookstoreapispring.book.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner run(BookRepository repository) {
        return (args -> {
            System.out.println(repository.findAll());
        });
    }
}
