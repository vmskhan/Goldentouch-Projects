package com.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.dao.TestDAO;
import com.dao.TestDAOImpl;
import com.dao.UserDAO;
import com.dao.UserDAOImpl;
import com.entities.Test;
import com.entities.User;
import com.service.UserService;
import com.service.UserServiceImpl;


@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan(basePackages = {"com"})
public class TestClient {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx =  SpringApplication.run(TestClient.class, args);
		Test test=new Test();
		test.setDate("12/10/00");
		test.setDuration(2.00f);
		test.setStart_time("2 pm");
		test.setState("edit");
		test.setTname("Placement");
		TestDAO dao = ctx.getBean("TestDAO",TestDAOImpl.class);
		dao.saveTest(test);
	}

}
