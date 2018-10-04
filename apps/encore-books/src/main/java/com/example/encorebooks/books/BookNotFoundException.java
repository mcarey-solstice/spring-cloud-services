package com.example.encorebooks.books;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Long id) {
        super(String.format("Could not find book with id of '%s'", id));
    }

}
