package com.example.library.books;

import com.example.library.ReadOnlyApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BookService extends ReadOnlyApiService<Book, Long> {

    // TODO: Replace with service registry

    @Value("${services.books.uri ?: http://localhost:8082}")
    private String baseUri;

    @Override
    protected String getBaseUri() {
        return String.format("%s/%s", baseUri, "books");
    }

}
