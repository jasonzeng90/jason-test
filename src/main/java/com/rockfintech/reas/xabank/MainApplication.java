package com.rockfintech.reas.xabank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ImportResource("classpath:applicationContext-rabbitmq.xml")
public class MainApplication {
	public static void main(String[] args) {
	       SpringApplication.run(MainApplication.class, args);
	}
}
