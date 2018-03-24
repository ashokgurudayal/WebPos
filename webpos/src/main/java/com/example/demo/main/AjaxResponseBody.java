package com.example.demo.main;

import java.util.List;

import com.example.demo.entity.Category;

public class AjaxResponseBody {
	String msg;
    List<Category> result;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<Category> getResult() {
		return result;
	}
	public void setResult(List<Category> result) {
		this.result = result;
	}
    
}
