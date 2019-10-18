package com.my.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.my.dao.BoardDAO;
import com.my.exception.AddException;
import com.my.exception.NotFoundException;
import com.my.vo.Board;
import com.my.vo.PageBean;

public class BoardService {
	private BoardDAO dao;
	public BoardService() {
		dao = new BoardDAO();
	}
	public com.my.vo.PageBean<Board>  boardList() throws NotFoundException{
		return boardList(1);
	}
	public com.my.vo.PageBean<Board> boardList(int currentPage) 
	                    throws NotFoundException{
		int cntPerPage = 3; //한페이지별 보여줄 목록수 
		int startRow = ((currentPage-1) * cntPerPage)+1;
		int endRow = currentPage * cntPerPage;
		List<Board> list = dao.select(startRow, endRow);
		
		int totalCnt = dao.count();
		int maxPage = (int)(Math.ceil((float)totalCnt/cntPerPage));
		
		int cntPerPageGroup = 4;//페이지그룹에서 보여줄 페이지수	
		//페이지그룹의 시작페이지,끝페이지 계산
		//ex: 총페이지수 10
		//현재페이지  시작페이지 끝페이지
		// 1          1      4
		// 2          1      4
		// 3          1      4
		// 4          1      4
		// 5          5      8
		// 6          5      8
		// 7          5      8  
		// 8          5      8
		// 9          9      10
		int startPage = 
				((currentPage-1) /cntPerPageGroup)*cntPerPageGroup + 1 ; 
	    int endPage = startPage + cntPerPageGroup -1;
	    if(endPage > maxPage) {
	    	endPage = maxPage;
	    }
		PageBean<Board> pb = 
				new PageBean<>();
		pb.setStartPage(startPage);
		pb.setEndPage(endPage);
		pb.setCurrentPage(currentPage);//현재페이지
		pb.setCntPerPage(cntPerPage);//페이지별 목록수
		pb.setList(list); //목록
		pb.setTotalCnt(totalCnt); //총건수
		pb.setMaxPage(maxPage);	//최대페이지수
		return pb;
	}	
	public static void main(String[] args) {		
		int cntPerPage = 3; //한페이지별 보여줄 목록수 
		int cntPerPageGroup = 4;//페이지그룹에서 보여줄 페이지수	
		int maxPage = 10;
		for(int currentPage=1; currentPage<=20; currentPage++) {
			int startPage = ((currentPage-1) /cntPerPageGroup)*cntPerPageGroup + 1 ;
			int endPage = startPage + cntPerPageGroup -1;
		    if(endPage > maxPage) {
		    	endPage = maxPage;
		    }
			System.out.println(currentPage+"=" + startPage + ":" + endPage);
		}
	}
	public Board boardDetail(int no) throws NotFoundException{
		return dao.selectByBoardNo(no);
	}
	public String boardwrite(Board board) {
		int status = -1;
		String msg = "글쓰기 실패";
		try {
			dao.insert(board);
			status = 1;			
		}catch(AddException e) {
			msg += e.getMessage();
			e.printStackTrace();
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", status);
		jsonObj.put("msg", msg);
		String str = jsonObj.toString();
		return str;
	}
	public String boardPwdChk(int board_no, String board_pwd) {
		int status = -1;
		try {
			Board board = dao.selectByBoardNo(board_no);
			if(board.getBoard_pwd().equals(board_pwd)) {
				status = 1;
			}
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", status);
		String str = jsonObj.toString();
		return str;
	}
	
	
	public String boardReply(Board board) {
		int status = -1;
		String msg = "답글쓰기 실패 ";
		try {
			dao.insert(board);
			status = 1;			
		}catch(AddException e) {
			msg += e.getMessage();
			e.printStackTrace();
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", status);
		jsonObj.put("msg", msg);
		String str = jsonObj.toString();
		return str;
	}

}
