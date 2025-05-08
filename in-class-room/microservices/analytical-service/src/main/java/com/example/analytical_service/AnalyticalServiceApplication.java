package com.example.analytical_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableKafka
public class AnalyticalServiceApplication {

	@KafkaListener(topics = "notifications", groupId = "analytics")
	public void listen(String message) {
		System.out.println("Received message: " + message);
	}

	public static void main(String[] args) {
		SpringApplication.run(AnalyticalServiceApplication.class, args);
	}

}
