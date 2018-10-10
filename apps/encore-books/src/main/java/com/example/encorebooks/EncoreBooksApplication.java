package com.example.encorebooks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class EncoreBooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncoreBooksApplication.class, args);
	}

}
