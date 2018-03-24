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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.AttributeAndValues;
import com.example.demo.entity.Attributes;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductEntity;
import com.example.demo.service.AttributeAndValuesService;
import com.example.demo.service.AttributeService;
import com.example.demo.service.ProductService;

@Controller
public class AttributeController {
	@Autowired
	private AttributeService attributeService;
	@Autowired
	private AttributeAndValuesService attributeAndValuesService;
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping("/attributes")
	@ResponseBody
	public List<Attributes> getAllAttributes(){
		List<Attributes> attr =attributeService.getAllAttributes(); 
		return attr;
	}
	
	@RequestMapping("/attributes/{id}")
	@ResponseBody
	public Attributes getTopic(@PathVariable Long id) {
		return attributeService.getAttributes(id);
	}
	
	@RequestMapping(value = "/addAttributes",method=RequestMethod.POST)
	public String addTopic(@Valid @ModelAttribute  Attributes attributes,BindingResult bindingResult,Model model) throws IOException {
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
		attributeService.addAttributes(attributes);
		model.addAttribute("message", "CATEGORY ADDED SUCCESSFULLY!!!");
		model.addAttribute(new Product());
		return "addproductbootstrap";
	}
	@RequestMapping(value = "/addAttributesAjax",method=RequestMethod.POST)
	@ResponseBody
	public String addAttributesAjax(@Valid @ModelAttribute  Attributes attributes,BindingResult errors,@RequestParam(value = "values", required = false) String values)  {
		String result ;
		if(errors.hasErrors()) {
			result = (errors.getAllErrors()
                    .stream().map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

			return result;
		}
		attributeService.addAttributes(attributes);
		for(String s:values.split(",")) {
			AttributeAndValues attributeAndValues = new AttributeAndValues();
			attributeAndValues.setValue(s);
			attributeAndValues.setAttributes(attributes);
			attributeAndValuesService.addAttributeAndValues(attributeAndValues);
		}
		result = ("Attributes saved successfully");
		return result;
	}
	@RequestMapping(value = "/attributes/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public void updateTopic(@RequestBody Attributes attributes,@PathVariable String id) {
		attributeService.updateAttributes(attributes,id);
	}
	
	@RequestMapping(value = "/attributes/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public void updateAttributes(@PathVariable Long id) {
		attributeService.deleteAttributes(id);
	}
}
