package com.my.service;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.CustomerDAO;
import com.my.exception.AddException;
import com.my.exception.NotFoundException;
import com.my.vo.Customer;

@Service
public class CustomerService {
	@Autowired
	private CustomerDAO dao;
	public CustomerService() {
		dao = new CustomerDAO();
	}
	public Map<String, Object> login(String id, String pwd) {
		int status=-1;
		try {
			Customer c = dao.selectById(id);
			if(c.getPwd().equals(pwd)) {
				status = 1;
			}
		} catch(NotFoundException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<>();
		map.put("status", status);
		map.put("id", id);
		return map;
	}
/*	public String login(String id, String pwd) {
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
	}*/
	public Map<String, Object> dupchk(String id) {
		int status = -1; //id중복 실패		
		try {
			dao.selectById(id);
			status = 1;//id중복 성공
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<>();
		map.put("status", status);
		return map;
	}
	public Map<String, Object> join(Customer c) {
		int status = -1; //가입 실패		
		try {
			dao.insert(c);
			status = 1;
		}catch(AddException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<>();
		map.put("status", status);
		return map;
	}
}
