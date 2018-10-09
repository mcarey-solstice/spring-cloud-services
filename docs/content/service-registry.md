# Service Registry

## Story
Hardcoding URIs can be a huge issue if one changes.  Thanks to service registry, all of your APIs can be registered through a common interface to allow applications using those services dynamic routing!

## Running the Application
Via Gradle
```sh
cd service-registry && ./gradlew bootRun
```

## Access the Application
The server will be running on http://localhost:8761

## Tutorial
All information can be found [here](https://spring.io/guides/gs/service-registration-and-discovery)

### Enabling the server
Add the `org.springframework.cloud:spring-cloud-starter-netflix-eureka-client` dependency and `org.springframework.cloud:spring-cloud-starter-eureka-server` test dependency the `build.gradle`:
```groovy
dependencies {
    compile('org.springframework.cloud:spring-cloud-starter-netflix-eureka-client')
    compile('org.springframework.boot:spring-boot-starter-web')
	testCompile('org.springframework.boot:spring-boot-starter-test')
	testCompile('org.springframework.cloud:spring-cloud-starter-eureka-server')
}
```

Add the `@EnableEurekaServer` annotation to the main Application class:
```java
@EnableEurekaServer
@SpringBootApplication
public class Application {
    // ...
}
```

For development, add the following to the `application.properties`:
```
server.port=8761

eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false

logging.level.com.netflix.eureka=OFF
logging.level.com.netflix.discovery=OFF
```

### Enabling the client
Add the `org.springframework.cloud:spring-cloud-starter-netflix-eureka-client` dependency and `org.springframework.cloud:spring-cloud-starter-eureka-server` test dependency the `build.gradle`:
```groovy
dependencies {
    compile('org.springframework.cloud:spring-cloud-starter-netflix-eureka-client')
    compile('org.springframework.boot:spring-boot-starter-web')
	testCompile('org.springframework.boot:spring-boot-starter-test')
	testCompile('org.springframework.cloud:spring-cloud-starter-eureka-server')
}
```

Add the `@EnableDiscoveryClient` annotation to the `Application.java`:
```java
@EnableDiscoveryClient
@SpringBootApplication
public class Application {
    //...
}
```

Make sure to add the application name to the `bootstrap.properties`:
```
spring.application.name=eureka-client
```
