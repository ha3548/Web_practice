package com.my.service;

import org.json.simple.JSONObject;

import com.my.dao.ZipDAO;
import com.my.exception.NotFoundException;
import com.my.vo.Customer;

public class ZipService {
	private ZipDAO dao;
	
	public ZipService() {
		dao = new ZipDAO();
	}
	
	public String searchZip(String doro) {
		int status = -1; // 성공
		try {
			Customer c = (Customer) dao.selectByDoro(doro);
			if (c.getPost().getDoro().equals(doro)) {
				status = 1; // 실패
			}
		} catch (NotFoundException e) {
			e.printStackTrace();
		}

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", status);
		String str = jsonObj.toString();
		
		return str;
	}
}
