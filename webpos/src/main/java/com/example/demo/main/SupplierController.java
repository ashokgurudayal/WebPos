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

import com.example.demo.entity.Supplier;
import com.example.demo.entity.Addresses;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductEntity;
import com.example.demo.service.SupplierService;
import com.example.demo.service.AddressesService;
import com.example.demo.service.ProductService;

@Controller
public class SupplierController {
	@Autowired
	private SupplierService supplierService;
	@Autowired
	private AddressesService addressesService;
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping("/supplier")
	@ResponseBody
	public List<Supplier> getAllProducts(){
		return supplierService.getAllSuppliers();
	}
	
	@RequestMapping("/supplier/{id}")
	@ResponseBody
	public Supplier getTopic(@PathVariable Long id) {
		return supplierService.getSupplier(id);
	}
	
	@RequestMapping(value = "/addSupplier",method=RequestMethod.POST)
	public String addTopic(@Valid @ModelAttribute  Supplier supplier,BindingResult bindingResult,Model model) throws IOException {
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
		
		supplierService.addSupplier(supplier);
		model.addAttribute("message", "CATEGORY ADDED SUCCESSFULLY!!!");
		model.addAttribute(new Product());
		return "addproductbootstrap";
	}
	@RequestMapping(value = "/addSupplierAjax",method=RequestMethod.POST)
	@ResponseBody
	public String addSupplierAjax(@Valid @ModelAttribute  Supplier supplier,@Valid @ModelAttribute  Addresses addresses,BindingResult errors)  {
		String result ;
		if(errors.hasErrors()) {
			result = (errors.getAllErrors()
                    .stream().map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

			return result;
		}
		addressesService.addAddresses(addresses);
		supplier.setAddresses(addresses);
		supplierService.addSupplier(supplier);
		
		result = ("Supplier saved successfully");
		return result;
	}
	@RequestMapping(value = "/supplier/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public void updateTopic(@RequestBody Supplier supplier,@PathVariable String id) {
		supplierService.updateSupplier(supplier,id);
	}
	
	@RequestMapping(value = "/supplier/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public void updateSupplier(@PathVariable Long id) {
		supplierService.deleteSupplier(id);
	}
}
