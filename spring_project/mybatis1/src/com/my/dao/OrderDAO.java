package com.my.dao;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.my.exception.AddException;
import com.my.exception.NotFoundException;
import com.my.vo.OrderInfo;
import com.my.vo.OrderLine;

public class OrderDAO {
	/**
	 * order_info 테이블에 주문기본정보 추가 order_line 테이블에 주문상세정보 추가
	 * 
	 * @param orderInfo 주문기본정보와 주문상세정보들
	 * @throws AddException
	 */

	public void insert(OrderInfo orderInfo) throws AddException {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		SqlSession session = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();

			String id = orderInfo.getCustomer().getId();
			session.insert("com.my.vo.Order.insertOrderInfo", id);

			List<OrderLine> lines = orderInfo.getOrderLines();
			for (OrderLine line : lines) {
				session.insert("com.my.vo.Order.insertOrderLine", line);
			}
			session.commit();
		} catch (Exception e) {
			throw new AddException(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * 아이디에 해당하는 주문정보[주문상세 포함]들을 반환한다 최근주문정보부터 저장된다(내림차순정렬)
	 * 
	 * @param id 주문자ID
	 * @return
	 * @throws NotFoundException 해당주문정보가 없다면 NotFoundException 발생
	 */
	public List<OrderInfo> selectById(String id) throws NotFoundException {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		SqlSession session = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession();
			List<OrderInfo> list = session.selectList("com.my.vo.Order.selectById", id);
			System.out.println("주문검색건수: " + list.size() +"\n");
			if (list.size() == 0) {
				throw new NotFoundException("아이디에 해당하는 주문정보가 없습니다.");
			}
			return list;
		} catch (Exception e) {
			throw new NotFoundException(e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * 날짜구간에 해당 주문정보들을 반환한다.
	 * 
	 * @param start 검색할 시작날짜(yy/MM/dd 포맷 사용)
	 * @param end   검색할 끝날자(yy/MM/dd 포맷 사용)
	 * @return
	 * @throws NotFoundException
	 */
	public List<OrderInfo> selectByDate(String start, String end) throws NotFoundException {
		return null;
	}

	public List<OrderInfo> selectAll() {
		return null;
	}
}
