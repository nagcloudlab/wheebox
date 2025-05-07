package com.example.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class AsyncExample {

    public static void main(String[] args) {


        // Future

        // ExecutorService executorService = java.util.concurrent.Executors.newFixedThreadPool(2);
        // Callable<String> task1 = () -> {
        //     Thread.sleep(2000);
        //     return "Task 1 completed";
        // };

        // Future<String> future = executorService.submit(task1);
        // //... do some other work
        // try {
        //     String result = future.get(); // This will block until the task is completed
        //     System.out.println(result);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // } finally {
        //     executorService.shutdown();
        // }


        // Example of using CompletableFuture
        ExecutorService team1Pool= java.util.concurrent.Executors.newFixedThreadPool(2);
        CompletableFuture<String> completableFuture= CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "oirder";
        },team1Pool);
        
        ExecutorService team2Pool= java.util.concurrent.Executors.newFixedThreadPool(2);
        completableFuture.thenAcceptAsync(order -> {
            // enrich order
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Enriching order: " + order);
        },team2Pool);

    }
    
}
