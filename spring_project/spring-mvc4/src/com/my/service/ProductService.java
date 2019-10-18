package com.my.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.dao.ProductDAO;
import com.my.exception.NotFoundException;
import com.my.vo.Product;

@Service
public class ProductService {
	@Autowired
	private ProductDAO dao;
	public ProductService() {
		dao = new ProductDAO();
	}
	
	public List<Product> productList() {
		List<Product> list = null;
		try {
			list = dao.selectAll();
			
			/*for(Product prod:list) {
				String cate_name = prod.getCategory().getCate_name();
				String prod_no = prod.getProd_no();
				String prod_name = prod.getProd_name();
				int prod_price = prod.getProd_price();
				
				//System.out.println("<제품정보>  분류:" + cate_name + ",\t상품번호:" + prod_no + ",\t상품이름:" + prod_name + ",\t상품가격:" + prod_price);
				//System.out.println("<제품정보>  상품번호:" + prod_no + ",\t상품이름:" + prod_name + ",\t상품가격:" + prod_price);
			}*/
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}

	public String productDatail(String prod_no) {
		int prod_cate_no = 0;
		String prod_name = null;
		int prod_price = 0;
		String prod_detail = null;
		Product product;
		
		try {
			product = dao.selectByNo(prod_no);
			prod_name = product.getProd_name();
			prod_price = product.getProd_price();
			prod_detail = product.getProd_detail();
			
			//System.out.println(prod_detail +":"+ prod_no +":"+ prod_name +":"+ prod_price);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("prod_no", prod_no);
		jsonObj.put("prod_name", prod_name);
		jsonObj.put("prod_price", prod_price);
		jsonObj.put("prod_detail", prod_detail);

		String str = jsonObj.toString();
		//System.out.println(str);
		return str;
		
		/*Map<String,Object> map = new HashMap<>();
		Product product;
		
		try {
			product = dao.selectByNo(prod_no);
			map.put("status", 1);
			map.put("product", product);
		} catch (NotFoundException e) {
			map.put("status", -1);
			e.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			//System.out.println(map);
			return mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}*/
	}

}
