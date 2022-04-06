package com.util;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Configuration
@ComponentScan({"com"})
@PropertySource(value = {"classpath:application.properties"})
@EnableTransactionManagement
public class HibernateConfiguration {
	@Autowired
	private Environment environment;
	public Environment getEnvironment() {
		return environment;
	}
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	
	@Bean("dataSource")
	public DataSource getDataSource() {
		System.out.println("getdatasource method called");
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(environment.getProperty("spring.datasource.url"));
		return dataSource;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager getTransactionManager(SessionFactory s) {
		System.out.println("gettransactionmanager method called");
		HibernateTransactionManager txManager=new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}
	
	@Bean("session")
	public LocalSessionFactoryBean getSessionFactory() {
		System.out.println("getsessionfactory method called");
		LocalSessionFactoryBean sessionFactory =new LocalSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setPackagesToScan(new String[] {"com"});
		sessionFactory.setHibernateProperties(getHibernateProperties());
		return sessionFactory;
	}
	
	public Properties getHibernateProperties() {
		System.out.println("gethibernateproperties method called");
		Properties prop=new Properties();
		prop.put("hibernate.dialect", environment.getProperty("spring.jpa.properties.hibernate.dialect"));
		prop.put("hibernate.show_sql", environment.getProperty("spring.jpa.show-sql"));
		//prop.put("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));
		prop.put("hibernate.connection.username", environment.getProperty("spring.datasource.username"));
		prop.put("hibernate.connection.password", environment.getProperty("spring.datasource.password"));
		prop.put("hibernate.hbm2ddl.auto", environment.getProperty("spring.jpa.hibernate.ddl-auto"));
		return prop;
	}
}