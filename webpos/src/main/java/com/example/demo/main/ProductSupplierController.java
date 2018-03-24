package com.example.demo.main;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Attributes;
import com.example.demo.entity.Company;
import com.example.demo.entity.Inventory;
import com.example.demo.entity.Outlets;
import com.example.demo.entity.PosUser;
import com.example.demo.entity.ProductSuppliers;
import com.example.demo.entity.ProductAttributes;
import com.example.demo.service.AttributeAndValuesService;
import com.example.demo.service.AttributeService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.InventoryService;
import com.example.demo.service.OutletsService;
import com.example.demo.service.ProductAttributesService;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductSuppliersService;
import com.example.demo.service.SupplierService;
import com.example.demo.service.UserService;

/**
 * The rest based controller that takes care of all the activities related to the Products 
 * @author Ashok Sen Gurudayal
 *
 */
@Controller
public class ProductSupplierController {
	
	@Autowired
	private ProductSuppliersService productSupplierService;
	
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping("/productsuppliers")
	@ResponseBody
	public List<ProductSuppliers> getAllProductSuppliers(){
		return productSupplierService.getAllProductSuppliers();
	}
	
	@RequestMapping("/productsupplier/{id}")
	@ResponseBody
	public ProductSuppliers getTopic(@PathVariable Long id) {
		return productSupplierService.getProductSuppliers(id);
	}
	
	
	
	
}
