package com.my.service;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.my.dao.ZipDAO;
import com.my.exception.NotFoundException;

public class ZipService {
	private ZipDAO dao;
	public ZipService() {
		dao = new ZipDAO();
	}
	public String searchZip(String doro) {
//		String result="["; 
//		try {
//			List<Map<String,String>> list = dao.selectByDoro(doro);
//			for(Map<String,String> map: list) {
//				String zipcode = map.get("zipcode");
//				String addrdoro = map.get("addrdoro");
//				String addrzibun = map.get("addrzibun");
//				String buildingno = map.get("buildingno");
//				result += "{\"zipcode\":\""+ zipcode +"\",\"addrdoro\":\""+ addrdoro+"\",\"addrzibun\":\"" + addrzibun + "\", \"buildingno\":\""+ buildingno+"\"}";				
//			}
//			
//		} catch (NotFoundException e) {			
//			e.printStackTrace();
//		}
//		result +="]";		
//		return result;
		JSONArray jsonArr = new JSONArray();
		try {
			List<Map<String,String>> list = dao.selectByDoro(doro);
			
			for(Map<String,String> map: list) {
				String zipcode = map.get("zipcode");
				String addrdoro = map.get("addrdoro");
				String addrzibun = map.get("addrzibun");
				String buildingno = map.get("buildingno");
				JSONObject jsonObj = new JSONObject();
				jsonObj.putAll(map);
				jsonArr.add(jsonObj);
			}
		}catch(NotFoundException e) {
			e.printStackTrace();			
		}
		System.out.println(jsonArr.toJSONString());
		System.out.println(jsonArr.toString());
		return jsonArr.toString();
	}

}
