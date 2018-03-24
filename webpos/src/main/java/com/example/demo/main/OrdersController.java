package com.example.demo.main;

import java.io.IOException;
import java.util.Date;
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
import com.example.demo.entity.Orders;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductEntity;
import com.example.demo.service.CategoryService;
import com.example.demo.service.OrdersService;
import com.example.demo.service.ProductService;

@Controller
public class OrdersController {
	@Autowired
	private OrdersService ordersService;
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping("/getallorders")
	@ResponseBody
	public List<Orders> getAllOrders(){
		Date d = new Date(1521568526L * 1000);
		System.out.println("wowowowow:"+d.getHours()+":"+d.getMinutes());
		return ordersService.getAllOrders();
	}
	
	
}
