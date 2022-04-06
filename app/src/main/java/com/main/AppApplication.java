package com.main;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan({"com"})
public class AppApplication {

	public static void main(String[] args) {
		ApplicationContext ctx= SpringApplication.run(AppApplication.class, args);

	}

}
