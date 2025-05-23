package com.example.springsecurity.configuration;

import java.sql.SQLException;

import javax.sql.DataSource;

import jakarta.annotation.PreDestroy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * The type Data source config.
 */
@Configuration
public class 
DataSourceConfig {

	/**
	 * The Database.
	 */
	private EmbeddedDatabase database = null;


	/**
	 * The Application context.
	 */
	private ApplicationContext applicationContext;

	/**
	 * Instantiates a new Data source config.
	 *
	 * @param applicationContext the application context
	 */
	public DataSourceConfig(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	/**
	 * Embedded H2 database
	 * Connect to running database with the following details:
	 * URL: jdbc:h2:mem:dataSource
	 * Driver Class: org.h2.Driver
	 * Username: sa
	 * Password: [blank]
	 *
	 * @return data source
	 */
	@Bean
	public DataSource dataSource() {
		final EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		database = builder.setType(EmbeddedDatabaseType.H2)
				.setName("dataSource")
				.ignoreFailedDrops(true)
				.continueOnError(false)
				.addScript("classpath:database/h2/calendar-schema.sql")
				.addScript("classpath:database/h2/calendar-data.sql")
				.build();
		return database;
	}

	/**
	 * Transaction manager platform transaction manager.
	 *
	 * @param dataSource the data source
	 * @return the platform transaction manager
	 */
	@Bean
	public PlatformTransactionManager transactionManager(final DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	/**
	 * Jdbc operations jdbc template.
	 *
	 * @param dataSource the data source
	 * @return the jdbc template
	 */
	@Bean
	public JdbcTemplate jdbcOperations(final DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}


	/**
	 * Used for JSR-303 Validation
	 *
	 * @return the local validator factory bean
	 */
	@Bean
	public LocalValidatorFactoryBean validatorFactoryBean() {
		return new LocalValidatorFactoryBean();
	}


	/**
	 * Method validation post processor method validation post processor.
	 *
	 * @return the method validation post processor
	 */
	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		return new MethodValidationPostProcessor();
	}


	/**
	 * DataSource PreDestroy call-back
	 *
	 * @throws SQLException the sql exception
	 */
	@PreDestroy()
	public void dataSourceDestroy() throws SQLException {

		SQLException sqlException = null;

		try {
			applicationContext.getBean(DataSource.class)
					.getConnection()
					.close();
		}
		catch (SQLException e) {
			sqlException = e;
			e.printStackTrace();
		}

		if (database != null) {
			database.shutdown();
		}

		if (sqlException != null) {
			throw sqlException;
		}
	}

} 
