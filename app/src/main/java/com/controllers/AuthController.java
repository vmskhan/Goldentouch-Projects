package com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.entities.User;
import com.service.UserService;

@Controller
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@RequestMapping(value="/",method = RequestMethod.GET)
	public String redirectToLogin() {
		System.out.println("redirectTOLogin method called");
		return "redirect:/login";
	}

	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public ModelAndView loadLoginPage(ModelAndView mandv,@RequestParam(required = false) String error) {
		System.out.println("loadloginpage method called");
		mandv.addObject("msg","");
		if(error != null) {
			mandv.addObject("msg","Invalid Username or Password");
		}
		mandv.addObject("user",new User());
		mandv.setViewName("Login");
		return mandv;
		
	}


	@RequestMapping(value="/signup",method = RequestMethod.GET)
	public ModelAndView loadSignupPage(ModelAndView mandv) {
		System.out.println("signup");
		mandv.addObject("user",new User());
		mandv.addObject("msg","");
		mandv.setViewName("Signup");
		return mandv;

	}
	
	
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	public ModelAndView processSignupPage(User user,ModelAndView mandv) {
		System.out.println("processsignuppage method called");
		// check if user already exists
		User tempUser = userService.findUserByEmailid(user.getEmailid());
		if(tempUser != null) {
			// user exists
			mandv.addObject("msg","Oops ! An account with the Email Id already exists");
			mandv.addObject("user",new User());
			mandv.setViewName("Signup");
			return mandv;
		}else {
			// create new user
			String password = user.getPassword();
			user.setPassword(passwordEncoder.encode(password));
			userService.saveUser(user);
			return new ModelAndView("redirect:/login");
		}
		
	}
	
}
