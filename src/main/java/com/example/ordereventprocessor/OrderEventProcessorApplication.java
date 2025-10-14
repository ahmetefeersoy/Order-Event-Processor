package com.example.ordereventprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class OrderEventProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderEventProcessorApplication.class, args);
	}

}
