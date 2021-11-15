package com.olympic.athletemanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@EnableAutoConfiguration
@SpringBootApplication
@EnableJpaAuditing
public class AthleteManagementSystemApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AthleteManagementSystemApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(AthleteManagementSystemApplication.class);
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(DataSize.ofBytes(500000000L));
		factory.setMaxRequestSize(DataSize.ofBytes(500000000L));
		return factory.createMultipartConfig();
	}
}
