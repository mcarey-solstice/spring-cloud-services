package com.example.library.movies;

import com.example.library.ReadOnlyApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MovieService extends ReadOnlyApiService<Movie, Long> {

    // TODO: Replace with service registry

    @Value("${services.movies.uri:http://localhost:8081}")
    private String baseUri;

    @Override
    protected String getBaseUri() {
        return String.format("%s/%s", baseUri, "movies");
    }

}
