package com.example.encorebooks.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class BookController {

    private BookRepository repository;

    @Autowired
    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/books")
    public Iterable<Book> getAllBooks() {
        return repository.findAll();
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody Book movie) {
        return repository.save(movie);
    }

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@RequestBody Book body, @PathVariable Long id) {
        return repository.findById(id)
                .map(movie -> {
                    movie.setTitle(body.getTitle());
                    movie.setAuthor(body.getAuthor());
                    movie.setPages(body.getPages());
                    return repository.save(movie);
                })
                .orElseThrow(() -> new BookNotFoundException(id));
    }

}
