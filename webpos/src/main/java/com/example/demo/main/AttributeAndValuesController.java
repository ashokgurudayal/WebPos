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

import com.example.demo.entity.AttributeAndValues;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductEntity;
import com.example.demo.service.AttributeAndValuesService;
import com.example.demo.service.ProductService;

@Controller
public class AttributeAndValuesController {
	@Autowired
	private AttributeAndValuesService attributeAndValuesService;
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping("/attributeAndValues")
	@ResponseBody
	public List<AttributeAndValues> getAllProducts(){
		return attributeAndValuesService.getAllAttributeAndValues();
	}
	
	@RequestMapping("/attributeAndValues/{id}")
	@ResponseBody
	public AttributeAndValues getTopic(@PathVariable Long id) {
		return attributeAndValuesService.getAttributeAndValues(id);
	}
	
	@RequestMapping(value = "/addAttributeAndValues",method=RequestMethod.POST)
	public String addTopic(@Valid @ModelAttribute  AttributeAndValues attributeAndValues,BindingResult bindingResult,Model model) throws IOException {
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
		attributeAndValuesService.addAttributeAndValues(attributeAndValues);
		model.addAttribute("message", "CATEGORY ADDED SUCCESSFULLY!!!");
		model.addAttribute(new Product());
		return "addproductbootstrap";
	}
	@RequestMapping(value = "/addAttributeAndValuesAjax",method=RequestMethod.POST)
	@ResponseBody
	public String addAttributeAndValuesAjax(@Valid @ModelAttribute  AttributeAndValues attributeAndValues,BindingResult errors)  {
		String result ;
		if(errors.hasErrors()) {
			result = (errors.getAllErrors()
                    .stream().map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

			return result;
		}
		attributeAndValuesService.addAttributeAndValues(attributeAndValues);
		result = ("AttributeAndValues saved successfully");
		return result;
	}
	@RequestMapping(value = "/attributeAndValues/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public void updateTopic(@RequestBody AttributeAndValues attributeAndValues,@PathVariable String id) {
		attributeAndValuesService.updateAttributeAndValues(attributeAndValues,id);
	}
	
	@RequestMapping(value = "/attributeAndValues/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public void updateAttributeAndValues(@PathVariable Long id) {
		attributeAndValuesService.deleteAttributeAndValues(id);
	}
}
