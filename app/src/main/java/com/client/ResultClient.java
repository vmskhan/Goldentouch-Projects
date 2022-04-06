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
import com.dao.ResultDAO;
import com.dao.ResultDAOImpl;
import com.dao.TestDAO;
import com.dao.TestDAOImpl;
import com.dao.UserDAO;
import com.dao.UserDAOImpl;
import com.entities.CompKey_Result;
import com.entities.Question;
import com.entities.Result;
import com.entities.Test;
import com.entities.User;
import com.service.UserService;
import com.service.UserServiceImpl;


@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan(basePackages = {"com"})
public class ResultClient {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx =  SpringApplication.run(ResultClient.class, args);
		Result res=new Result();
		CompKey_Result comp=new CompKey_Result();
		comp.setTid(100);
		comp.setUid(1000);
		res.setCompkey(comp);
		res.setTotal(20);
		ResultDAO dao = ctx.getBean("ResultDAO",ResultDAOImpl.class);
		dao.saveResult(res);
	}

}
