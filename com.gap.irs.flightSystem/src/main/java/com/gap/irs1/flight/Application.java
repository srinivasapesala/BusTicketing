package com.gap.irs1.flight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages="com.gap.irs1.flight")
@ComponentScan("com.gap.irs1.flight")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
