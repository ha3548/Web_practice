package com.my.service;

import org.json.simple.JSONObject;

import com.my.dao.CustomerDAO;
import com.my.exception.AddException;
import com.my.exception.NotFoundException;
import com.my.vo.Customer;

public class CustomerService {
	private CustomerDAO dao;
	public CustomerService() {
		dao = new CustomerDAO();
	}
	public String login(String id, String pwd) {
		int status = -1; //로그인 실패
		try {
			Customer c = dao.selectById(id);
			if(c.getPwd().equals(pwd)) {
				status = 1;
			}
		}catch(NotFoundException e) {
			e.printStackTrace();
		}
		//String str = "{\"status\":" + status +", \"id\": \"" + id +"\"}";
		
		//json-simple lib활용
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", status);
		jsonObj.put("id", id);
		String str = jsonObj.toString();
		return str;
	}
	public String dupchk(String id) {
		int status = -1; //id중복 실패		
		try {
			dao.selectById(id);
			status = 1;//id중복 성공
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		//String str = "{\"status\":" + status +"}";
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", status);
		String str = jsonObj.toString();
		return str;
	}
	public String join(Customer c) {
		int status = -1; //가입 실패		
		try {
			dao.insert(c);
			status = 1;
		}catch(AddException e) {
			e.printStackTrace();
		}
		//String str = "{\"status\":" + status +"}";
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", status);
		String str = jsonObj.toString();
		return str;
		
	}
}
