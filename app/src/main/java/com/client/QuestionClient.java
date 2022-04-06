package com.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.dao.QuestionDAO;
import com.dao.QuestionDAOImpl;
import com.dao.TestDAO;
import com.dao.TestDAOImpl;
import com.dao.UserDAO;
import com.dao.UserDAOImpl;
import com.entities.Question;
import com.entities.Test;
import com.entities.User;
import com.service.UserService;
import com.service.UserServiceImpl;


@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan(basePackages = {"com"})
public class QuestionClient {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx =  SpringApplication.run(QuestionClient.class, args);
		Question ques=new Question();
		ques.setQuestionText("Hello");
		ques.setMark(2);
		ques.setAnswerText("Correct");
		QuestionDAO dao = ctx.getBean("QuestionDAO",QuestionDAOImpl.class);
		dao.saveQuestion(ques);
	}

}
