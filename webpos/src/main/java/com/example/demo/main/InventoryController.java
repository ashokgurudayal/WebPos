package com.example.demo.main;

import java.io.IOException;
import java.util.List;
import java.util.Map;
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

import com.example.demo.entity.Inventory;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductEntity;
import com.example.demo.service.InventoryService;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductSuppliersService;


@Controller
public class InventoryController {
	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private ProductSuppliersService productSupplierService;
	
	@RequestMapping(value = "/updateInventoryAjax",method=RequestMethod.POST)
	@ResponseBody
	public String addInventoryAjax(@RequestParam MultiValueMap<String,String> reqParams)  {
		String result ;
		List <String> ids = reqParams.get("prodsupplierid");
		List <String> units = reqParams.get("units");
		List <String> weight = reqParams.get("weight");
		for(int i=0;i<ids.size();i++) {
			System.out.println("okkayyyyyyyyyyyyyyyyyyyyaaaaa");
			productSupplierService.updateUnitsAndWeight(Long.parseLong(ids.get(i)),Integer.parseInt(units.get(i)), Double.parseDouble(weight.get(i)));
		}
		
		result = ("Inventory saved successfully");
		return result;
	}
	@RequestMapping(value = "/inventory/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public void updateTopic(@RequestBody Inventory inventory,@PathVariable String id) {
		inventoryService.updateInventory(inventory,id);
	}
	@RequestMapping("/inventory/{id}")
	@ResponseBody
	public Inventory getInventory(@PathVariable Long id) {
		return inventoryService.getInventory(id);
	}
	
}
