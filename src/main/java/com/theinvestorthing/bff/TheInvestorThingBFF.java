package com.theinvestorthing.bff;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TheInvestorThingBFF {
	private final static Logger logger = LoggerFactory.getLogger(TheInvestorThingBFF.class);

	@Value("${spring.application.name}")
	private String name;

	@Value("${spring.application.version}")
	private String version;

	@Value("${spring.application.module}")
	private String module;

	@Value("${spring.application.author}")
	private String author;


	public static void main(String[] args) {
		SpringApplication.run(TheInvestorThingBFF.class, args);
	}

	@PostConstruct
	public void applicationInfo(){
		logger.info("Application Info - Name: {} | Module: {} | Version: {} | Author: {}", name, module, version, author);
	}

}
