package com.example.wheebox;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WheeboxAutoConfiguration {
    @Bean
    public String bean1() {
        return "bean-1";
    }
}
