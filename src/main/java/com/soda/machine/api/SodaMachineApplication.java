package com.soda.machine.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SodaMachineApplication {
	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/soda-machine-api");
		SpringApplication.run(SodaMachineApplication.class, args);
	}
}
