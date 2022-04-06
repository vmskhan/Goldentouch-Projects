package com.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.dao.UserDAO;
import com.dao.UserDAOImpl;
import com.entities.User;
import com.service.UserService;
import com.service.UserServiceImpl;


@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan(basePackages = {"com"})
public class UserClient {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx =  SpringApplication.run(UserClient.class, args);
		User user = new User();
		user.setName("Yash");
		user.setPassword("yashyash");
		user.setEmailid("yash@gmail.com");
		UserService dao = ctx.getBean("UserService",UserServiceImpl.class);
		dao.saveUser(user);
	}

}
