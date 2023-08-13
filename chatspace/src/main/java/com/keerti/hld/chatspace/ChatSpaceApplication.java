package com.keerti.hld.chatspace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ChatSpaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatSpaceApplication.class, args);
	}

}