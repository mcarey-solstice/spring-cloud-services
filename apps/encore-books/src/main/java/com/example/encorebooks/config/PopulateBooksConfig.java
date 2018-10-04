package com.example.encorebooks.config;

import com.example.encorebooks.books.Book;
import com.example.encorebooks.books.BookRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "books.populate")
public class PopulateBooksConfig {

    @Autowired
    private BookRepository repository;

    private Book book(String title, String author, Integer pages) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPages(pages);
        return book;
    }

    @Bean
    InitializingBean seedDatabase() {
        return () -> {
            repository.save(book("Book 1", "John Doe", 1000));
            repository.save(book("Book 2", "Jane Doe", 607));
        };
    }
}
