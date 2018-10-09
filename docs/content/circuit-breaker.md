# Circuit Breaker Dashboard

## Story
What if the movie service is down?  Should you not be able to view data on books and music?  Netflix circuit breaker gives you the ability to fallback if a service is not available to avoid downtime!

## Running the Application
Via Gradle
```sh
cd circuit-breaker && ./gradlew bootRun
```

## Access the Application
The server will be running on http://localhost:8090

## Tutorial
All information can be found [here](https://spring.io/guides/gs/circuit-breaker)

## Enabling the server'
Add the
spring-cloud-stream-dependencies

### Enabling the client
Add the `org.springframework.cloud:spring-cloud-starter-netflix-hystrix` dependency to the `build.gradle`:
```groovy
dependencies {
    compile('org.springframework.cloud:spring-cloud-starter-netflix-hystrix')
    compile('org.springframework.boot:spring-boot-starter-web')
    testCompile('org.springframework.boot:spring-boot-starter-test')
}
```

Add the `@EnableCircuitBreaker` annotation to the main Application class:
```java
@EnableCircuitBreaker
@SpringBootApplication
public class Application {
    // ...
}
```

For local development, change the port in the `application.properties`:
```
server.port=8090
```

Apply the circuit breaker pattern:
```java
@Service
public class BookService {

    private final RestTemplate restTemplate;

    @Autowired
    public BookService(RestTemplate restTemplate) { this.restTemplate = restTemplate; }

    @HystrixCommand(fallbackMethod = "reliable")
    public String readingList() {
        URI uri = URI.create("http://localhost:8090/reading-list");

        return this.restTemplate.getForObject(uri, String.class);
    }

    public String reliable() { return "No reading list could be obtained at the moment"; }

}
```
