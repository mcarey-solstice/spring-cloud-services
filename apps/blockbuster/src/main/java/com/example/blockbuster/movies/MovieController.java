package com.example.blockbuster.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MovieController {

    private MovieRepository repository;

    @Autowired
    public MovieController(MovieRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/movies")
    public Iterable<Movie> getAllMovies() {
        return repository.findAll();
    }

    @PostMapping("/movies")
    public Movie createMovie(@RequestBody Movie movie) {
        return repository.save(movie);
    }

    @GetMapping("/movies/{id}")
    public Movie getMovie(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));
    }

    @PutMapping("/movies/{id}")
    public Movie updateMovie(@RequestBody Movie body, @PathVariable Long id) {
        return repository.findById(id)
                .map(movie -> {
                    movie.setTitle(body.getTitle());
                    movie.setDirector(body.getDirector());
                    movie.setDuration(body.getDuration());
                    return repository.save(movie);
                })
                .orElseThrow(() -> new MovieNotFoundException(id));
    }

}
