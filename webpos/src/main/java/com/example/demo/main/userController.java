package com.example.demo.main;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.PosUser;
import com.example.demo.entity.ProductEntity;
import com.example.demo.service.UserService;

@Controller
public class userController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/adduser",method=RequestMethod.POST)
	public String addTopic(@Valid @ModelAttribute  PosUser posUser,BindingResult bindingResult,Model model) throws IOException {
		if(bindingResult.hasErrors()) {
			return "userlogin";
		}
		userService.addUser(posUser);
		model.addAttribute("message", "ADDED SUCCESSFULLY!!!");
		return "userlogin";
	}
}
