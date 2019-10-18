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

	public String searchZip(String doro) throws NotFoundException {
		List<Map<String, String>> list = dao.selectByDoro(doro);

		JSONArray jsonArr = new JSONArray();

		for (int i = 0; i < list.size(); i++) {
			Map<String, String> map = list.get(i);

			JSONObject jsonObj = new JSONObject();
			jsonObj.put("zipcode", map.get("zipcode"));
			jsonObj.put("addrdoro", map.get("addrdoro"));
			jsonObj.put("addrzibun", map.get("addrzibun"));
			jsonObj.put("buildingno", map.get("buildingno"));

			jsonArr.add(jsonObj);
		}

		String result = jsonArr.toString();
		return result;

	}
}
