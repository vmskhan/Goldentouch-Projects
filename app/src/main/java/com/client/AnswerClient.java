package com.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.dao.AnswerDAO;
import com.dao.AnswerDAOImpl;
import com.dao.QuestionDAO;
import com.dao.QuestionDAOImpl;
import com.dao.TestDAO;
import com.dao.TestDAOImpl;
import com.dao.UserDAO;
import com.dao.UserDAOImpl;
import com.entities.Answer;
import com.entities.Question;
import com.entities.Test;
import com.entities.User;
import com.service.UserService;
import com.service.UserServiceImpl;


@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan(basePackages = {"com"})
public class AnswerClient {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx =  SpringApplication.run(AnswerClient.class, args);
		Answer ans=new Answer();
//		ans.setQid(3000);
//		ans.setOptions("Correctanswer");
		AnswerDAO dao = ctx.getBean("AnswerDAO",AnswerDAOImpl.class);
		dao.saveAnswer(ans);
	}

}
