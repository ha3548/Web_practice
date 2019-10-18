package com.my.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.my.exception.NotFoundException;
import com.my.vo.OrderInfo;
@Service
public interface OrderService {
	/**
	 * 주문정보를 추가한다
	 * @param info
	 * @return
	 */
	String add(OrderInfo info);
	
	/**
	 * 아이디에 해당하는 주문정보들을 반환한다
	 * @param id 아이디
	 * @return
	 */
	List<OrderInfo> findById(String id) throws NotFoundException;
}
