package com.example.demo.main;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ProductEntity;
import com.example.demo.service.ProductService;

/**
 * The rest based controller that takes care of all the activities related to the Products 
 * @author Ashok Sen Gurudayal
 *
 */
@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping("/products")
	@ResponseBody
	public List<ProductEntity> getAllProducts(){
		return productService.getAllProducts();
	}
	
	@RequestMapping("/product/{id}")
	@ResponseBody
	public ProductEntity getTopic(@PathVariable String id) {
		return productService.getProduct(id);
	}
	
	@RequestMapping(value = "/addproduct",method=RequestMethod.POST)
	public String addTopic(@Valid @ModelAttribute  ProductEntity productEntity,BindingResult bindingResult,Model model) throws IOException {
		
		if(bindingResult.hasErrors()) {
			return "addproduct";
		}
		productService.addProduct(productEntity);
		model.addAttribute("message", "ADDED SUCCESSFULLY!!!");
		return "addproduct";
	}
	
	@RequestMapping(value = "/products/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public void updateTopic(@RequestBody ProductEntity product,@PathVariable String id) {
		productService.updateProduct(product,id);
	}
	
	@RequestMapping(value = "/products/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public void updateProduct(@PathVariable String id) {
		productService.deleteProduct(id);
	}
}
