package com.zac.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringbootThymeleafJpaApplication {
	
	final static Logger logger = LoggerFactory.getLogger(SpringbootThymeleafJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringbootThymeleafJpaApplication.class, args);
	}


}
