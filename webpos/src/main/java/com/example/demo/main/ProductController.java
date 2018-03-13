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
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductAttributes;
import com.example.demo.entity.ProductSuppliers;
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
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private AttributeService attributeService;
	
	@Autowired
	private AttributeAndValuesService attributeAndValuesService;
	
	@Autowired
	private ProductAttributesService productAttributesService;
	
	@Autowired
	private OutletsService outletsService;
	
	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductSuppliersService productSupplierService;
	
	@Autowired
	private SupplierService supplierService;
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping("/products")
	@ResponseBody
	public List<Product> getAllProducts(){
		return productService.getAllProducts();
	}
	
	@RequestMapping("/product/{id}")
	@ResponseBody
	public Product getTopic(@PathVariable Long id) {
		return productService.getProduct(id);
	}
	
	@RequestMapping(value = "/addproduct",method=RequestMethod.POST)
	public String addProduct(@Valid @ModelAttribute  Product product,@RequestParam String suppliers,@RequestParam String tax,@RequestParam String categoryy,@RequestParam String attributeName,@RequestParam String attributeValue, BindingResult bindingResult,Model model) throws IOException {
		
		if(bindingResult.hasErrors()) {
			return "addproductbootstrap";
		}
		//Set tax if selected is default tax
		if(tax.equalsIgnoreCase("default")) {
			product.setTaxPercentage(13.0);
		}
		//Calculate and set the tax value
		product.setTaxvalue(product.getCostPrice()*(product.getTaxPercentage()/100.0));
		
		//Set the category link
		product.setCategory(categoryService.findByCategoryName(categoryy));
		
		productService.addProduct(product);
		
		//Add all the attributes
		ProductAttributes productAttributes = new ProductAttributes();
		productAttributes.setProduct(product);
		//Attributes attributesss = attributeService.findByName(attributeName);
		productAttributes.setAttributeAndValues(attributeAndValuesService.findAttributeValuesEntryAttributeAndValue(attributeName,attributeValue));
		productAttributesService.addProductAttributes(productAttributes);
		
		//Add entries in inventory table
		
		Inventory inventory = new Inventory();
		inventory.setProduct(product);
		PosUser user = (PosUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Outlets> outlets = outletsService.findByCompany(userService.findCompanyOfUser(user.getUsername()));
		inventory.setOutlets(outlets.get(0));
		inventoryService.addInventory(inventory);
		
		ProductSuppliers productSuppliers = new ProductSuppliers();
		productSuppliers.setInventory(inventory);
		productSuppliers.setSupplier(supplierService.findBySupplierName(suppliers));
		productSupplierService.addProductSuppliers(productSuppliers);
		
		//Set the attribute and values link
		model.addAttribute("message", "ADDED SUCCESSFULLY!!!");
		return "addproductbootstrap";
	}
	
	@RequestMapping(value = "/products/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public void updateTopic(@RequestBody Product product,@PathVariable Long id) {
		productService.updateProduct(product,id);
	}
	
	@RequestMapping(value = "/products/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public void updateProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
	}
}
