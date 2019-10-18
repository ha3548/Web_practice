package com.my.service;

import java.sql.Timestamp;
import java.util.List;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.ProductDAO;
import com.my.exception.NotFoundException;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;
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
			
			for(Product prod:list) {
				String cate_name = prod.getCategory().getCate_name();
				String prod_no = prod.getProd_no();
				String prod_name = prod.getProd_name();
				int prod_price = prod.getProd_price();
				
				//System.out.println("<제품정보>  분류:" + cate_name + ",\t상품번호:" + prod_no + ",\t상품이름:" + prod_name + ",\t상품가격:" + prod_price);
				//System.out.println("<제품정보>  상품번호:" + prod_no + ",\t상품이름:" + prod_name + ",\t상품가격:" + prod_price);
			}
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
	}

}
