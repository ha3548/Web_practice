package com.my.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.exception.NotFoundException;
import com.my.vo.Product;

@Repository
public class ProductDAO {
	@Autowired
	private SqlSessionFactory SqlSessionFactory;
	
	public List<Product> selectAll() throws NotFoundException{
		SqlSession sqlSession = SqlSessionFactory.openSession();

		List<Product> list;
		list = sqlSession.selectList("com.my.vo.Product.selectAll");
		//System.out.println("상품검색건수: " + list.size() +"\n");
		if (list.size() == 0) {
			throw new NotFoundException("상품정보가 없습니다.");
		}
		sqlSession.close();
		return list;
	}

	public Product selectByNo(String prod_no) throws NotFoundException{
		SqlSession sqlSession = SqlSessionFactory.openSession();
		Product product = sqlSession.selectOne("com.my.vo.Product.selectByNo", prod_no);
		if(product==null) {
			throw new NotFoundException("상품이 존재하지 않습니다.");
		}
		sqlSession.close();
		return product;
	}
}
