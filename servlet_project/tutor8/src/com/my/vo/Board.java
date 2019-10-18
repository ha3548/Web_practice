package com.my.vo;

import java.util.Date;

public class Board {
	/*
BOARD_NO      NOT NULL NUMBER        
PARENT_NO              NUMBER        
BOARD_SUBJECT NOT NULL VARCHAR2(30)  
BOARD_WRITER  NOT NULL VARCHAR2(30)  
BOARD_CONTENT NOT NULL VARCHAR2(300) 
BOARD_TIME    NOT NULL DATE          
BOARD_PWD     NOT NULL VARCHAR2(10)  
	 */
	private int board_no;
	private int parent_no;
	private int level;
	private String board_subject;
	private String board_writer;
	private String board_content;
	private Date board_time;
	private String board_pwd;
	public Board() {
		super();
	}
	public Board(int board_no, int parent_no, int level, String board_subject, String board_writer,
			String board_content, Date board_time, String board_pwd) {
		super();
		this.board_no = board_no;
		this.parent_no = parent_no;
		this.level = level;
		this.board_subject = board_subject;
		this.board_writer = board_writer;
		this.board_content = board_content;
		this.board_time = board_time;
		this.board_pwd = board_pwd;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public int getParent_no() {
		return parent_no;
	}
	public void setParent_no(int parent_no) {
		this.parent_no = parent_no;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getBoard_subject() {
		return board_subject;
	}
	public void setBoard_subject(String board_subject) {
		this.board_subject = board_subject;
	}
	public String getBoard_writer() {
		return board_writer;
	}
	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public Date getBoard_time() {
		return board_time;
	}
	public void setBoard_time(Date board_time) {
		this.board_time = board_time;
	}
	public String getBoard_pwd() {
		return board_pwd;
	}
	public void setBoard_pwd(String board_pwd) {
		this.board_pwd = board_pwd;
	}
}
