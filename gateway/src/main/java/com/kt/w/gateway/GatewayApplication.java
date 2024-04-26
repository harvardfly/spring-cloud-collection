package com.kt.w.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.ZonedDateTime;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		System.out.println(ZonedDateTime.now());
		SpringApplication.run(GatewayApplication.class, args);
	}

}
