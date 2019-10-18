package com.my.vo;

import java.sql.Timestamp;
import java.util.List;

public class OrderInfo {
	private int order_no;
	//private String order_id;
	private Customer customer;
	private Timestamp order_time;
	//★★★One의 입장에서 Many를 가지는 관계★★★
	private List<OrderLine> orderLines;
	
	public OrderInfo() {
		super();
	}
	public OrderInfo(int order_no, Customer customer, Timestamp order_time, List<OrderLine> orderLines) {
		super();
		this.order_no = order_no;
		this.customer = customer;
		this.order_time = order_time;
		this.orderLines = orderLines;
	}
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Timestamp getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Timestamp order_time) {
		this.order_time = order_time;
	}
	public List<OrderLine> getOrderLines() {
		return orderLines;
	}
	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
	
}
