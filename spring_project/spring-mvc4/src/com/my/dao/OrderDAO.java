package com.my.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.my.exception.AddException;
import com.my.exception.NotFoundException;
import com.my.vo.OrderInfo;

public interface OrderDAO {
	/**
	 * order_info 테이블에 주문기본정보 추가 order_line 테이블에 주문상세정보 추가
	 * 
	 * @param orderInfo 주문기본정보와 주문상세정보들
	 * @throws AddException
	 */
	public void insert(OrderInfo orderInfo) throws AddException;
	
	/**
	 * 아이디에 해당하는 주문정보[주문상세 포함]들을 반환한다 최근주문정보부터 저장된다(내림차순정렬)
	 * 
	 * @param id 주문자ID
	 * @return
	 * @throws NotFoundException 해당주문정보가 없다면 NotFoundException 발생
	 */
	public List<OrderInfo> selectById(String id) throws NotFoundException;
	
	/**
	 * 날짜구간에 해당 주문정보들을 반환한다.
	 * 
	 * @param start 검색할 시작날짜(yy/MM/dd 포맷 사용)
	 * @param end   검색할 끝날자(yy/MM/dd 포맷 사용)
	 * @return
	 * @throws NotFoundException
	 */
	public List<OrderInfo> selectByDate(String start, String end) throws NotFoundException;
	
	public List<OrderInfo> selectAll();
}
