package com.example.library.books;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Long id) {
        super(String.format("Could not find book with id of '%s'", id));
    }

}
