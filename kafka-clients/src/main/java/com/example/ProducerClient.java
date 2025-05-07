package com.example;

import java.util.List;

import org.apache.kafka.clients.producer.KafkaProducer;

public class ProducerClient {
    public static void main(String[] args) {


        // Kafka producer properties
        java.util.Properties properties = new java.util.Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // Create a producer record
        String topic = "ets";
        List<String> cities = List.of("chennai", "bangalore", "delhi", "mumbai");
        for (int i = 0; i < Long.MAX_VALUE; i++) {
            // choose random city
            String key = cities.get((int) (Math.random() * cities.size()));
            String value = "Hello, Kafka!";
            org.apache.kafka.clients.producer.ProducerRecord<String, String> record = new org.apache.kafka.clients.producer.ProducerRecord<>(
                    topic, key, value);
            // Send the record
            producer.send(record, (metadata, exception) -> {
                if (exception != null) {
                    System.err.println("Error sending message: " + exception.getMessage());
                } else {
                    System.out
                            .println("Message sent to topic " + metadata.topic() + " partition " + metadata.partition()
                                    + " offset " + metadata.offset());
                }
            });

        }
        
        // Close the producer
        producer.close();
        System.out.println("Producer closed.");


    }
}