package com.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public AuthenticationProvider autheticationProvider() {
		System.out.println("authenticationProvider method called");
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
		System.out.println("configure method called");
		auth.authenticationProvider(autheticationProvider());
	}
	
	@Bean
	public PasswordEncoder encoder() {
		System.out.println("encoder method called");
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
		System.out.println("myauthenticationsuccesshandler method called");
		return new LoginAuthenticationSuccessHandler();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("configure1 method called");
		http
			.csrf()
			.disable()
			.authorizeRequests()
			.antMatchers("/","/signup","/img/*","/styles/*","/payment/**","/oauther/**").permitAll()
			.antMatchers("/user/**").hasAnyAuthority("user")
			.antMatchers("/admin/**").hasAnyAuthority("admin")
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.successHandler(myAuthenticationSuccessHandler())
			.permitAll()
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
		
//		http.csrf()
//			.disable()
//			.authorizeRequests()
//			.antMatchers("**")
//			.permitAll()
//			.and()
//			.formLogin()
//			.loginPage("/login")
//			.successHandler(myAuthenticationSuccessHandler())
//			.permitAll()
//			.and()
//			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");

	}
}
