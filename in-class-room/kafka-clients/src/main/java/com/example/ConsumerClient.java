package com.example;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

public class ConsumerClient {

    public static void main(String[] args) {

        // Create a properties object to configure the consumer
        java.util.Properties properties = new java.util.Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "cg1");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.offset.reset", "earliest");
        properties.put("max.poll.records", "500");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        // Subscribe to the topic
        consumer.subscribe(java.util.Collections.singletonList("wheebox"));

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down consumer...");
            consumer.wakeup();
        }));

       try{
         // Poll for new messages
        while (true) {
            org.apache.kafka.clients.consumer.ConsumerRecords<String, String> records = consumer
                    .poll(java.time.Duration.ofMillis(100));
            for (org.apache.kafka.clients.consumer.ConsumerRecord<String, String> record : records) {
                System.out.printf("Consumed message: key = %s, value = %s%n", record.key(), record.value());
                // Process the message
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        } catch (WakeupException e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
       }
    
    
}
