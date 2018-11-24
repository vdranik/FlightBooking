package com.vdranik.fr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.vdranik.fr"})
public class FrApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrApplication.class, args);
	}
}
