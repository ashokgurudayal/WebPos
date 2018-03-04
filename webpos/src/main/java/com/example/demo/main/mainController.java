package com.example.demo.main;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.entity.PosUser;
import com.example.demo.entity.ProductEntity;

@Controller
public class mainController {
	@RequestMapping("/")
	public String userLogin(Model model) {

		model.addAttribute("loggedoutusername","Loggedout");
		model.addAttribute(new PosUser());
		return "userlogin";
	}

	@RequestMapping("/home")
	public String person(Model model) {
		PosUser user = (PosUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String name = user.getFirstName()+" "+user.getLastName(); // get logged in username
		model.addAttribute("username", name);

		return "home";
	}
	@RequestMapping("/showproducts")
	public String showProducts(Model model) {
		return "showproducts";
	}
	
	@RequestMapping(value = "/addproduct", method = RequestMethod.GET)
	public String addProduct(Model model) {
		model.addAttribute(new ProductEntity());
		return "addproduct";
	}

	@RequestMapping("/removeproduct")
	public String removeProduct(Model model) {
		return "removeproduct";
	}

	@RequestMapping("/adjustinventory")
	public String adjustInventory(Model model) {
		return "adjustinventory";
	}

	@RequestMapping("basic/leftsidebar")
	public String leftSideBar(Model model) {
		return "basic/leftsidebar";
	}

	@RequestMapping("basic/headerportion")
	public String header(Model model) {
		return "basic/headerportion";
	}

	@RequestMapping("basic/footerportion")
	public String footer(Model model) {
		return "basic/footerportion";
	}

	@RequestMapping("/sample")
	public String sample(Model model) {
		return "sample";
	}

	@RequestMapping("/pos")
	public String pospage(Model model) {
		return "pos";
	}
}
