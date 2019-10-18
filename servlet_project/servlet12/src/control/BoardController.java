package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.exception.NotFoundException;
import com.my.service.BoardService;
import com.my.vo.Board;
import com.my.vo.PageBean;

public class BoardController {
	private BoardService service;
	/*	 public BoardController() { 
	  	service = new BoardService(); 
	 }*/
	static private BoardController controller = new BoardController();
	private BoardController() {
		service = new BoardService();
	}
	static public BoardController getInstance() {
		return controller;
	}
	
	public String boardList(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		String currentPage = request.getParameter("currentPage");
		int intCurrentPage = 1;
		if(currentPage!=null) {
			intCurrentPage = Integer.parseInt(currentPage);
		}
		try {
			PageBean<Board> pb = service.boardlist(intCurrentPage);
			request.setAttribute("pb", pb);
			request.setAttribute("status", 1);
		} catch(NotFoundException e) {
			e.printStackTrace();
			request.setAttribute("status", -1);
		}
		String path = "/boardlistresult.jsp";
		return path;
	}
	
	public String boardDetail(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		String str_board_no = request.getParameter("board_no");
		int board_no = 1;
		if(str_board_no!=null) {
			board_no = Integer.parseInt(str_board_no);
		}
		try {
			Board board = service.boardDetail(board_no);
			request.setAttribute("board", board);
			request.setAttribute("status", 1);
		} catch(NotFoundException e) {
			e.printStackTrace();
			request.setAttribute("status", -1);
		}
		String path = "/boarddetailresult.jsp";
		return path;

	}
	
	public String boardWrite(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		String board_subject = request.getParameter("board_subject");
		String board_writer = request.getParameter("board_writer");
		String board_pwd = request.getParameter("board_pwd");
		String board_content = request.getParameter("board_content");
		
		Board b = new Board(board_subject, board_writer, board_pwd, board_content);
		String str = service.boardwrite(b);
		request.setAttribute("result", str);
		
		String path = "/result.jsp";
		return path;
	}
	
	public String boardReply(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		String board_subject = request.getParameter("board_subject");
		String board_writer = request.getParameter("board_writer");
		String board_pwd = request.getParameter("board_pwd");
		String board_content = request.getParameter("board_content");
		String str_parent_no = request.getParameter("parent_no");
		
		int parent_no = 0;
		if(str_parent_no!=null) {
			parent_no = Integer.parseInt(str_parent_no);
		}
		
		Board b = new Board(parent_no, board_subject, board_writer, board_pwd, board_content);
		String str = service.boardreply(b);
		request.setAttribute("result", str);
		
		String path = "/result.jsp";
		return path;
	}
}
