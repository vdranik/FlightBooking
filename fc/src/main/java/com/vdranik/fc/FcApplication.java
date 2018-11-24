package com.vdranik.fc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.vdranik.fc"})
public class FcApplication {

	public static void main(String[] args) {
		SpringApplication.run(FcApplication.class, args);
	}
}
