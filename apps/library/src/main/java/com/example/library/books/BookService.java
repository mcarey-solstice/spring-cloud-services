package com.example.library.books;

import com.example.library.ReadOnlyApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BookService extends ReadOnlyApiService<Book, Long> {

//    @Value("${services.books.uri ?: http://localhost:8082}")
//    private String baseUri;
//
//    @Override
//    protected String getBaseUri() {
//        return String.format("%s/%s", baseUri, "books");
//    }

    @Value("${services.movies.serviceName:encore-books}")
    private String serviceName;

    @Override
    protected String getServiceName() {
        return serviceName;
    }

    @Override
    protected String getContextPath() {
        return "books";
    }

}
