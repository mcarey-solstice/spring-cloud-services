package com.example.library.movies;

public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException(Long id) {
        super(String.format("Could not find movie with id of '%s'", id));
    }

}
