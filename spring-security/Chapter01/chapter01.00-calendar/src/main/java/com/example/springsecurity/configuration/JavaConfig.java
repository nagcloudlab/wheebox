package com.example.springsecurity.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * The type Java config.
 */
@Configuration
@Import(DataSourceConfig.class)
@ComponentScan(basePackages =
		{
				"com.example.springsecurity.dataaccess",
				"com.example.springsecurity.domain",
				"com.example.springsecurity.service"
		}
)
public class JavaConfig {} 
