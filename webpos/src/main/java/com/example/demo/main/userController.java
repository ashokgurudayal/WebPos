package com.example.demo.main;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Company;
import com.example.demo.entity.Outlets;
import com.example.demo.entity.PosUser;
import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.CompanyService;
import com.example.demo.service.OutletsService;
import com.example.demo.service.UserService;


/**
 * The controller that is used to take care of the user level functionalities.
 * @author Ashok Sen Gurudayal
 *
 */
@Controller
public class userController {
	@Autowired
	private UserService userService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private OutletsService outletsService;
	@Autowired
	private RoleRepository roleRepository;
	
	@RequestMapping(value = "/getallemployees")
	@ResponseBody
	public List<PosUser> getAllEmployeesOfCompany(){
		PosUser currentUser = (PosUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Company company = userService.findCompanyOfUser(currentUser.getUsername());
		List<PosUser> posEmployeeUsers = userService.findAllUsersOfCompany(company);
		
		//Remove all the admin users
		for(int i=0;i<posEmployeeUsers.size();) {
			if(posEmployeeUsers.get(i).getRoles().contains(roleRepository.findOne(1))) {
				posEmployeeUsers.remove(i);
			}
			else {
				i++;
			}
		}
		return posEmployeeUsers;
	}
	
	@RequestMapping(value = "/addemployee",method=RequestMethod.POST)
	public String addEmployee(@Valid @ModelAttribute  PosUser posUser,BindingResult bindingResult,Model model) throws IOException {
		if(bindingResult.hasErrors()) {
			return "userloginbootstrap";
		}
		PosUser currentUser = (PosUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		posUser.setCompany(userService.findCompanyOfUser(currentUser.getUsername()));
		userService.addEmployeeUser(posUser);
		model.addAttribute("message", "ADDED SUCCESSFULLY!!!");
		return "addemployee";
	}
	@RequestMapping(value = "/adduser",method=RequestMethod.POST)
	public String addTopic(@Valid @ModelAttribute  PosUser posUser,BindingResult bindingResult,Model model) throws IOException {
		if(bindingResult.hasErrors()) {
			return "userloginbootstrap";
		}
		//check if the company name is already taken up
		//If not,then add the user and the company details into the database
		if(companyService.findByCompanyName(posUser.getCompany().getName())==null) {
			//Save the company details into the database and then save the POS User entity
			Company company = posUser.getCompany();
			company.setDescription("Testing the company details");
			companyService.addCompany(company);
			posUser.setCompany(company);
			//Create a default outlet-1 outlet application
			Outlets outlet = new Outlets();
			outlet.setCompany(company);
			outletsService.addOutlets(outlet);
			
			userService.addUser(posUser);
			model.addAttribute("message", "ADDED SUCCESSFULLY!!!");
			return "userloginbootstrap";
		}
		//If the company is already taken up,then show up a message that the company name is already taken
		else {
			bindingResult.rejectValue("company.name", "error.user", "An account already exists for this email.");
			return "userloginbootstrap";
		}
		
		
	}
}
