package com.my.vo;

public class Customer {
	private String id;
	private String pwd;
	private String name;
	//private String buildingno; //?
	private Post post;
	private String addr;
	
	
	//생성자 : 매개변수없는 생성자, 모든인스턴스변수 초기화하는 생성자
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Customer(String id, String pwd, String name, Post post, String addr) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.post = post;
		this.addr = addr;
	}

	
	//setter/	getter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
	
}
