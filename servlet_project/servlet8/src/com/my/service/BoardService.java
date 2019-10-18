package com.my.service;

import java.util.List;

import com.my.dao.BoardDAO;
import com.my.exception.NotFoundException;
import com.my.vo.Board;
import com.my.vo.PageBean;

public class BoardService {
	private BoardDAO dao;
	public BoardService() {
		dao = new BoardDAO();
	}
	
	public PageBean<Board> boardlist() throws NotFoundException{
		//return dao.select(1,10);
		return boardlist(1);
	}

	public com.my.vo.PageBean<Board> boardlist(int currentPage) throws NotFoundException{
		int cntPerPage = 3;//한페이지에 보여줄 게시물수
		int startRow = cntPerPage * (currentPage-1) + 1;
		int endRow = cntPerPage * currentPage;
		List<Board> list = dao.select(startRow,endRow);
		
		int totalCnt = dao.count();
		int maxPage = (int)(Math.ceil((float)totalCnt/cntPerPage));
		
		//currentPage	start	end
		//     1		1		4
		//     2		1		4
		//     3		1		4
		//     4		1		4
		//     5		5		8
		//	   6		5		8
		//	   7		5		8
		//	   8		5		8
		//	   9		9		10
		int cntPerPageGroup = 4;//한 막대에서 보여줄 페이지수
		int endPage = ((currentPage-1)/cntPerPageGroup + 1)*cntPerPageGroup; //끝페이지
		int startPage = endPage-cntPerPageGroup+1; //시작페이지
		if(endPage > maxPage) {
	    	endPage = maxPage;
	    }
		
		PageBean<Board> pb = new PageBean<>();
		pb.setCurrentPage(currentPage);//현재페이지
		pb.setCntPerPage(cntPerPage);//페이지별 목록수
		pb.setList(list);//목록
		pb.setTotalCnt(totalCnt);//총 게시물수
		pb.setMaxPage(maxPage);//최대 페이지수

		pb.setStartPage(startPage);
		pb.setEndPage(endPage);
		
		return pb;
	}
	
/*	
	public List<Board> boardlist(int currentPage) throws NotFoundException{
		//int cntPerPage = 10; //한페이지당 보여줄 목록수
		int cntPerPage = 3;
		//currentPage	start	end
		//     1		1		10
		//     2		11		20
		//     3		21		30
		//     4		31		40
		//     5		41		50
		
		int startRow = cntPerPage * (currentPage-1) + 1;
		int endRow = cntPerPage * currentPage;
		return dao.select(startRow,endRow);
	}
	
	public int maxPage() throws NotFoundException {
		int cntPerPage=3;

		int totalCnt = dao.count();
		return (int)(Math.ceil((float)totalCnt/cntPerPage));
		
	}
*/
/*	
	public static void main(String[] args) {
		
		for(int i=1;i<=20;i++) {
			int cntPerPageGroup = 4;//한 막대에서 보여줄 페이지수
			int endPage = ((i-1)/cntPerPageGroup + 1)*cntPerPageGroup; //끝페이지
			int startPage = endPage-cntPerPageGroup+1; //시작페이지
			System.out.println(i+":"+startPage+"~"+endPage);
		}
	}
*/
}
