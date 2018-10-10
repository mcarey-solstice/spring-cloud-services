package com.example.library;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class ReadOnlyApiService<T, ID> {

    protected abstract String getServiceName();

    protected abstract String getContextPath();

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private EurekaClient eurekaClient;

    private InstanceInfo getInstanceInfo() {
        return eurekaClient.getApplication(getServiceName()).getInstances().get(0);
    }

    private String getBaseUri() {
        return String.format("%s://%s:%s/%s", "http", getInstanceInfo().getHostName(), getInstanceInfo().getPort(), getContextPath());
    }

    private URI getUri(String endpoint) {
        return URI.create(String.format("%s/%s", getBaseUri(), endpoint));
    }

    private URI getUri() {
        return getUri("");
    }

    private URI getUri(ID id) {
        return getUri("" + id);
    }

    public Optional<T> findById(ID id) {
        ResponseEntity<T> response = restTemplate.exchange(
                getUri(id),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<T>() {});
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            T body = response.getBody();
            if (body == null) {
                return Optional.empty();
            }

            return Optional.of(body);
        }

        return null;
    }

    public List<T> findAll() {
        ResponseEntity<List<T>> response = restTemplate.exchange(
                getUri(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<T>>(){});
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            return response.getBody();
        }

        return null;
    }

    public long count(T t) {
        return findAll().size();
    }

    public List<T> find(Iterable<ID> ids) {
        List<T> list = new ArrayList<>();
        for (ID id : ids) {
            list.add(findById(id).get());
        }

        return list;
    }

    public List<T> fallback() {
        return new ArrayList<>();
    }
}
