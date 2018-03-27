package com.example.demo.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Category;
import com.example.demo.entity.Orders;
import com.example.demo.entity.PosUser;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class mainController {
	@RequestMapping("/addemployee")
	public String addEmployee(Model model) {
		model.addAttribute(new PosUser());
		return "addemployee";
	}
	@RequestMapping(value="/download", method=RequestMethod.GET) 
	public ResponseEntity<Object> downloadFile() throws IOException  {
		String filename = "./new.xls";
		File file = new File(filename);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/txt")).body(resource);
		return responseEntity;
		
		
	}
	
	@RequestMapping("/graphicalreports")
	public String graphicalreports(Model model) {
		model.addAttribute(new PosUser());
		return "graphicalreports";
	}
	@RequestMapping("/addsupplier")
	public String addSupplier() {
		return "addsupplier";
	}
	@RequestMapping("/showsupplier")
	public String showSupplier() {
		return "showsupplier";
	}
	@RequestMapping("/")
	public String userLogin(Model model) {

		model.addAttribute("loggedoutusername","Loggedout");
		model.addAttribute(new PosUser());
		return "userloginbootstrap";
	}
	@RequestMapping("/showsales")
	public String showSales(Model model) {
		return "showsales";
	}
	@RequestMapping("/showemployee")
	public String showEmployee(Model model) {
		return "showemployeesbootstrap";
	}
	@RequestMapping("/adjustinventory")
	public String adjustInventory(Model model){
		return "adjustinventory";
	}
	@RequestMapping("/posbootstrap")
	public String posBootstrap(Model model){
		return "posbootstrap";
	}
	@RequestMapping("/posbootstrapnewformat")
	public String posBootstrapnewlayout(Model model){
		return "posbootstrapnewlayout";
	}
	@RequestMapping("/posbootstrapnewformatemployee")
	public String posbootstrapnewformatemployee(Model model){
		return "posbootstrapnewlayoutemployee";
	}
	@RequestMapping("/R")
	public String userLoginR(Model model) {

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
		return "showproductsbootstrap";
	}
	@RequestMapping("/testingDynamicFilter")
	public String testingDynamicFilter(Model model) {
		return "testingDynamicFilter";
	}
	@RequestMapping(value = "/addproduct", method = RequestMethod.GET)
	public String addProduct(Model model) {
		model.addAttribute(new ProductEntity());
		return "addproduct";
	}
	
	@RequestMapping(value = "/addproductbootstrap", method = RequestMethod.GET)
	public String addProductBootstrap(Model model) {
		model.addAttribute(new Product());
		//model.addAttribute(new Category());
		return "addproductbootstrap";
	}

	@RequestMapping("/removeproduct")
	public String removeProduct(Model model) {
		return "removeproduct";
	}

	

	@RequestMapping("basic/leftsidebar")
	public String leftSideBar(Model model) {
		return "basic/leftsidebarbootstrap";
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
		return "posbootstrap";
	}
	@RequestMapping("/posemployee")
	public String pospageemployee(Model model) {
		return "posbootstrapemployee";
	}
	@RequestMapping("/testingpagee")
	public String testingpagee(Model model) {
		return "testingpage";
	}
	@RequestMapping("/categoryaddtest")
	public String categoryaddtest(Model model) {
		return "categoryaddtest";
	}
}
