package com.example.demo.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

import com.example.demo.entity.Category;
import com.example.demo.entity.OrderItems;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductEntity;
import com.example.demo.service.CategoryService;
import com.example.demo.service.OrdersService;
import com.example.demo.service.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;



@Controller
public class OrdersController {
	@Autowired
	private OrdersService ordersService;
	
	private static List<Orders> orderDetailsSaved;
	private static String excelHeader;
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping("/getallorders")
	@ResponseBody
	public List<Orders> getAllOrders(){
		Date d = new Date(1521568526L * 1000);
		System.out.println("wowowowow:"+d.getHours()+":"+d.getMinutes());
		return ordersService.getAllOrders();
	}
	
	@RequestMapping(value="/getallordersafter",method=RequestMethod.POST)
	@ResponseBody
	public List<Orders> getAllOrdersafter(@RequestParam Date d){
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DATE, +1);
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		excelHeader = "Details of Orders on"+format.format(d);
		orderDetailsSaved = ordersService.findByCreatetsBetween(d,c.getTime());
		return orderDetailsSaved;
	}
	@RequestMapping(value="/createtheexcelfile", method=RequestMethod.GET) 
	@ResponseBody
	public String createtheexcelfile() throws IOException  {
		SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yy hh:mm a");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Sample sheet");
		
//		Map<String, Object[]> data = new HashMap<String, Object[]>();
//		data.put("1", new Object[] {excelHeader});
//		int orderno=1,rowCounter=2;
//		String k="k";
//		for(Orders order:orderDetailsSaved) {
//			data.put(k+rowCounter++, new Object[] {"Order No:"+orderno++});
//			data.put(k+rowCounter++, new Object[] {"Created Time:"+format.format(order.getCreatets())});
//			data.put(k+rowCounter++, new Object[] {"Sl.No","Product","Units","Weight","Cost Price","Tax"});
//			
//			for(OrderItems orderItems:order.getOrderItems()) {
//				data.put(k+rowCounter++, new Object[] {"Order No:"+orderno++});
//				System.out.println(Integer.toString(rowCounter));
//			}
//		}
		
		List<Object[]> dataList = new ArrayList<>();
		int orderno=1;int colCount;
		dataList.add(new Object[] {excelHeader});
		for(Orders order:orderDetailsSaved) {
			dataList.add(new Object[] {"Order No:"+orderno++});
			dataList.add(new Object[] {"Created Time:"+format.format(order.getCreatets())});
			dataList.add(new Object[] {"Sl.No","Product","Units","Weight","Cost Price","Tax"});
			colCount = 1;
			for(OrderItems orderItems:order.getOrderItems()) {
				dataList.add(new Object[] {colCount,orderItems.getInventory().getProduct().getName(),orderItems.getUnits(),orderItems.getWeight(),orderItems.getCostPrice(),orderItems.getTaxValue()});
				colCount++;
			}
			dataList.add(new Object[] {" "});
		}
		
		//Set<String> keyset = data.keySet();
		int rownum = 0;
		for (Object[] objArr : dataList) {
			Row row = sheet.createRow(rownum++);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if(obj instanceof Date) 
					cell.setCellValue((Date)obj);
				else if(obj instanceof Boolean)
					cell.setCellValue((Boolean)obj);
				else if(obj instanceof String)
					cell.setCellValue((String)obj);
				else if(obj instanceof Double)
					cell.setCellValue((Double)obj);
				else if(obj instanceof Integer)
					cell.setCellValue((Integer)obj);
			}
		}
		
		try {
			FileOutputStream out = 
					new FileOutputStream(new File("./new.xls"));
			workbook.write(out);
			out.close();
			System.out.println("Excel written successfully..");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Ok";
	}
	@RequestMapping(value="/getallordersbetween",method=RequestMethod.POST)
	@ResponseBody
	public List<Orders> getAllOrdersbetween(@RequestParam Date datestart,@RequestParam Date dateend){
		Calendar c = Calendar.getInstance();
		c.setTime(dateend);
		c.add(Calendar.DATE, +1);
		return ordersService.findByCreatetsBetween(datestart,c.getTime());
	}
	@RequestMapping(value="/getallordersmonth",method=RequestMethod.POST)
	@ResponseBody
	public List<Orders> getAllOrdersMonth(@RequestParam int month){
		LocalDate todaydate = LocalDate.now();
		todaydate = todaydate.withDayOfMonth(1);
		todaydate = todaydate.withMonth(month+1);
		
		return ordersService.findByCreatetsBetween(Date.from(todaydate.atStartOfDay(ZoneId.systemDefault()).toInstant()),Date.from(todaydate.withMonth(month+2).atStartOfDay(ZoneId.systemDefault()).toInstant()));
	}
	
	@RequestMapping(value="/getallcoordinates",method=RequestMethod.POST)
	@ResponseBody
	public List<GraphCoordinates> getAllCoordinates(int month){
		System.out.println("Month:"+month);
		
		//Find the last date of the selected month
		LocalDate todaydate = LocalDate.now();
		todaydate = todaydate.withMonth(month+1);
		
		//To set all the dates of a month with total sales value as 0
		List<GraphCoordinates> gc = new ArrayList<>();
		for(int i=1;i<=todaydate.lengthOfMonth();i++) {
			gc.add(new GraphCoordinates(Integer.toString(i), 0));
		}
		
		//Get all the orders corresponding to the selected month
		todaydate = todaydate.withDayOfMonth(1);
		List<Orders> ordersOfMonth = ordersService.findByCreatetsBetween(Date.from(todaydate.atStartOfDay(ZoneId.systemDefault()).toInstant()),Date.from(todaydate.withMonth(month+2).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		
		if(ordersOfMonth.isEmpty()) {
			return new ArrayList<>();
		}
		//Update the total sales value of the dates
		for(Orders order:ordersOfMonth) {
			gc.set(order.getCreatets().getDate()-1, new GraphCoordinates(Integer.toString(order.getCreatets().getDate()), gc.get(order.getCreatets().getDate()-1).y+order.getTotal_amount()) );
		}
		
		return gc;
	}
}
