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
import org.springframework.util.MultiValueMap;
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
import com.example.demo.entity.OrderItems;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.OrderItemsRepository;
import com.example.demo.service.AttributeAndValuesService;
import com.example.demo.service.AttributeService;
import com.example.demo.service.InventoryService;
import com.example.demo.service.OrderItemsService;
import com.example.demo.service.OrdersService;
import com.example.demo.service.ProductService;

@Controller
public class PosController {
	@Autowired
	OrdersService ordersService; 
	
	@Autowired
	InventoryService inventoryService;
	
	@Autowired 
	ProductService productService;
	
	@Autowired
	OrderItemsService orderItemsService;
	
	@RequestMapping(value = "/posbilling",method=RequestMethod.POST)
	@ResponseBody
	public String addBillAjax(@RequestParam MultiValueMap<String, String> requestParams)  {
		System.out.println(requestParams.get("productid").get(0));
		//Add the product details added to the bill
		Orders orders = new Orders();
		orders.setOrder_type("instore");
		orders.setTotal_amount(Double.parseDouble(requestParams.get("totalcostprice").get(0)));
		orders.setTotal_tax(Double.parseDouble(requestParams.get("totaltax").get(0)));
		ordersService.addOrders(orders);
		
		//Save the order items in the order items table
		for(int i=0;i<requestParams.get("productid").size();i++) {
			OrderItems orderItems = new OrderItems();
			orderItems.setOrders(orders);
			orderItems.setInventory(inventoryService.findByProduct(productService.getProduct(Long.parseLong(requestParams.get("productid").get(i)))));
			orderItems.setUnits(Integer.parseInt(requestParams.get("units").get(i)));
			orderItems.setWeight(Double.parseDouble(requestParams.get("weight").get(i)));
			orderItems.setCostPrice(Double.parseDouble(requestParams.get("costprice").get(i)));
			orderItems.setTaxValue(Double.parseDouble(requestParams.get("tax").get(i)));
			orderItemsService.addOrderItems(orderItems);
		}
		
		return "Order Saved!!!";
	}
	
}
