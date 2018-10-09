# Config Server

## Story
Configurations change all the time.  Let's change configurations and allow the app to refresh the values itself.

## Running the Application
Via Gradle
```sh
cd config-server && ./gradlew bootRun
```

## Access the Application
The server will be running on http://localhost:8888

## Tutorial
All information can be found [here](https://spring.io/guides/gs/centralized-configuration)

## Enable the server
Add the `org.springframework.cloud:spring-cloud-config-server` dependency to the `build.gradle`:
```groovy
dependencies {
    compile('org.springframework.cloud:spring-cloud-config-server')
    testCompile('org.springframework.boot:spring-boot-starter-test')
}
```

Add the `@EnableConfigServer` annotation to the main Application class:
```java
@EnableConfigServer
@SpringBootApplication
public class Application {
    // ...
}
```

Configure a configuration resource in the `application.properties`:
```
server.port=8888

spring.cloud.config.server.git.uri=file:///${user.home}/workspace/config
```

## Enable the client
Add the `org.springframework.cloud:spring-cloud-starter-config` and `org.springframework.boot:spring-boot-starter-actuator` dependencies to the `build.gradle`:
```groovy
dependencies {
    compile('org.springframework.cloud:spring-cloud-starter-config')
    compile('org.springframework.boot:spring-boot-starter-actuator')
    compile('org.springframework.boot:spring-boot-starter-web')
}
```

Simply add the configuration to the `bootstrap.yml` and autoconfiguration will pick up the rest:
```
spring.cloud.config.uri=http://localhost:8888
```

Add the `/refresh` management endpoint to the server for refreshing on the fly.  Enabled by adding the follwoing to the `application.properties`:
```
management.endpoints.web.exposure.include=*
```

Don't forget to add the `@RefreshScope` annotation for your `@ConfigurationProperties` and `@Value` properties:
```java
@RefreshScope
@RestController
public class MessageRestController {

    @Value("${message:Hello default}")
    private String message;

    @RequestMapping("/message")
    String getMessage() {
        return this.message;
    }
}
```
