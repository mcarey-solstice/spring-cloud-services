package com.example.library.movies;

import com.example.library.ReadOnlyApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MovieService extends ReadOnlyApiService<Movie, Long> {

//    @Value("${services.movies.uri?:http://localhost:8081}")
//    private String baseUri;
//
//    @Override
//    protected String getBaseUri() {
//        return String.format("%s/%s", baseUri, "movies");
//    }

    @Value("${services.movies.serviceName:blockbuster}")
    private String serviceName;

    @Override
    protected String getServiceName() {
        return serviceName;
    }

    @Override
    protected String getContextPath() {
        return "movies";
    }

}
