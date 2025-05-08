package com.wheebox;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name="city")
public class WheeboxAutoConfiguration {
    @Bean
    public String bean1() {
        return "bean-1";
    }
}
