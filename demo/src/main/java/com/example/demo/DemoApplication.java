package com.example.demo;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
public class DemoApplication {

	@GetMapping("/hello")
	public Mono<String> hello() throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " - Processing request");
		// Mono with delay
		// Simulate a delay of 2 seconds
		return Mono.just("Hello, World!")
				.delayElement(Duration.ofSeconds(2))
				.doOnNext(result -> System.out.println(Thread.currentThread().getName() + " - Response: " + result));

	}
	
    @Autowired
    private ThirdPartyQueueProcessor processor;

    @PostMapping("/api/process")
    public ResponseEntity<String> handleRequest(@RequestBody RequestData data) {
        processor.enqueue(data);
        return ResponseEntity.accepted().body("Request accepted and will be processed soon.");
    }

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
