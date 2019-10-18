package com.my.service;

import java.util.List;

import com.my.dao.BoardDAO;
import com.my.exception.NotFoundException;
import com.my.vo.Board;

public class BoardService {
	private BoardDAO dao;
	public BoardService() {
		dao = new BoardDAO();
	}
	
	public List<Board> boardlist() throws NotFoundException{
		return dao.select();
	}
	
}
