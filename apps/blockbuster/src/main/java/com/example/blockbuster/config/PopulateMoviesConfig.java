package com.example.blockbuster.config;

import com.example.blockbuster.movies.Movie;
import com.example.blockbuster.movies.MovieRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "services.movies.populate")
public class PopulateMoviesConfig {

    @Autowired
    private MovieRepository repository;

    private Movie movie(String title, String director, Long duration) {
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setDirector(director);
        movie.setDuration(duration);
        return movie;
    }

    @Bean
    InitializingBean seedDatabase() {
        return () -> {
            repository.save(movie("Movie 1", "John Doe", 6000L));
            repository.save(movie("Movie 2", "Jane Doe", 9000L));
        };
    }
}
