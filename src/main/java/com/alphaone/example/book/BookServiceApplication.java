package com.alphaone.example.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BookServiceApplication {

	private static Logger LOGGER = LoggerFactory.getLogger(BookServiceApplication.class);
	
	public static void main(String[] args) {
		LOGGER.info("Starting up service " + BookServiceApplication.class.getName());		
		SpringApplication.run(BookServiceApplication.class, args);
	}
}
