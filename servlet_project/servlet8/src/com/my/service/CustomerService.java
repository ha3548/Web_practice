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
		int status = -1; // 로그인실패
		try {
			Customer c = dao.selectById(id);
			if (c.getPwd().equals(pwd)) {
				status = 1; // 로그인성공
			}
		} catch (NotFoundException e) {
			e.printStackTrace();
		}

		//String str = "{\"status\":" + status + ", \"id\": \"" + id + "\"}";
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", status);
		jsonObj.put("id", id);
		String str = jsonObj.toString();
		return str;
	}

	public String dupchk(String id) {
		int status = -1; // 아이디중복안됨

		CustomerDAO dao = new CustomerDAO();
		try {
			Customer c = dao.selectById(id);
			if (c.getId().equals(id)) {
				status = 1; // 아이디중복
			}
		} catch (NotFoundException e) {
			e.printStackTrace();
		}

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", status);
		jsonObj.put("id", id);
		String str = jsonObj.toString();
		return str;
	}

	public String join(Customer c) {
		try {
			dao.insert(c);
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("status", 1);
			String str = jsonObj.toString();
			return str; // 가입성공

		} catch (AddException e) {
			e.printStackTrace();
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("status", -1);
			String str = jsonObj.toString();
			return str; // 가입실패
		}
	}
}
