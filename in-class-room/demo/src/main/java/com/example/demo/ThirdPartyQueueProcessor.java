package com.example.demo;


import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.vavr.control.Try;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.*;

@Component
public class ThirdPartyQueueProcessor {

    private final BlockingQueue<RequestData> queue = new LinkedBlockingQueue<>(1000);
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    private final RateLimiter rateLimiter;

    public ThirdPartyQueueProcessor() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitRefreshPeriod(Duration.ofSeconds(1))
                .limitForPeriod(100)
                .timeoutDuration(Duration.ofMillis(0))
                .build();
        this.rateLimiter = RateLimiter.of("thirdPartyLimiter", config);
    }

    @PostConstruct
    public void startProcessing() {
        executor.scheduleAtFixedRate(this::processQueue, 0, 10, TimeUnit.MILLISECONDS);
    }

    public void enqueue(RequestData requestData) {
        if (!queue.offer(requestData)) {
            throw new RuntimeException("Queue full. Try again later.");
        }
    }

    private void processQueue() {
        for (int i = 0; i < 10; i++) {
            RequestData data = queue.poll();
            if (data != null) {
                Try.runRunnable(RateLimiter.decorateRunnable(rateLimiter, () -> callThirdParty(data)))
                   .onFailure(ex -> retryLater(data));
            }
        }
    }

    private void callThirdParty(RequestData data) {
        System.out.println("Calling third-party for user: " + data.getUserId() + " with payload: " + data.getPayload());
        // Simulate success/failure
        if (Math.random() < 0.1) {
            throw new RuntimeException("Simulated third-party failure");
        }
    }

    private void retryLater(RequestData data) {
        System.out.println("Retrying for user: " + data.getUserId());
        queue.offer(data);
    }
}
