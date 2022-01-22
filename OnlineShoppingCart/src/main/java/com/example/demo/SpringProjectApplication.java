package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages= {"controller","services"})
@EntityScan("com.baseclasses")
@EnableJpaRepositories("dao")

public class SpringProjectApplication {

	public static void main(String[] args) throws Exception {
//		ConfigurableApplicationContext ctx=
		SpringApplication.run(SpringProjectApplication.class, args);		
	}
	

}
