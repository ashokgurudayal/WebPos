package com.example.demo.main;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductEntity;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;

@Controller
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping("/category")
	@ResponseBody
	public List<Category> getAllProducts(){
		return categoryService.getAllCategories();
	}
	
	@RequestMapping("/category/{id}")
	@ResponseBody
	public Category getTopic(@PathVariable Long id) {
		return categoryService.getCategory(id);
	}
	
	@RequestMapping(value = "/addCategory",method=RequestMethod.POST)
	public String addTopic(@Valid @ModelAttribute  Category category,BindingResult bindingResult,Model model) throws IOException {
		if(bindingResult.hasErrors()) {
			model.addAttribute(new Product());
			if(bindingResult.getFieldErrorCount("name")>0) {
				model.addAttribute("ErrorName",bindingResult.getFieldError("name"));
			}
			if(bindingResult.getFieldErrorCount("description")>0) {
				model.addAttribute("ErrorDescription",bindingResult.getFieldError("name"));
			}
			return "addproductbootstrap";
		}
		categoryService.addCategory(category);
		model.addAttribute("message", "CATEGORY ADDED SUCCESSFULLY!!!");
		model.addAttribute(new Product());
		return "addproductbootstrap";
	}
	@RequestMapping(value = "/addCategoryAjax",method=RequestMethod.POST)
	@ResponseBody
	public String addCategoryAjax(@Valid @ModelAttribute  Category category,BindingResult errors)  {
		String result ;
		if(errors.hasErrors()) {
			result = (errors.getAllErrors()
                    .stream().map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

			return result;
		}
		categoryService.addCategory(category);
		result = ("Category saved successfully");
		return result;
	}
	@RequestMapping(value = "/category/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public void updateTopic(@RequestBody Category category,@PathVariable String id) {
		categoryService.updateCategory(category,id);
	}
	
	@RequestMapping(value = "/category/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public void updateCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
	}
}
