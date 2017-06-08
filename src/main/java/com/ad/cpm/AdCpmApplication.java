package com.ad.cpm;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RestController;

// extends SpringBootServletInitializer
@SpringBootApplication
@RestController
public class AdCpmApplication   {
//
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(Application.class);
//	}
	public static void main(String[] args) {
		SpringApplication.run(AdCpmApplication.class, args);
	}
}
