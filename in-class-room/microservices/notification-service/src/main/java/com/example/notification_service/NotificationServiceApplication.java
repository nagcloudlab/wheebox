package com.example.notification_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableKafka
public class NotificationServiceApplication {


	@KafkaListener(topics = "notifications", groupId = "notification-group")
	public void consumeNotificationMessage(String message) {
		System.out.println("Received message: " + message);
	}

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

}
