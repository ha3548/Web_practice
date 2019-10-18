package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.exception.NotFoundException;
import com.my.service.BoardService;
import com.my.vo.Board;
import com.my.vo.PageBean;

public class BoardController {
	private BoardService service;
//	public BoardController() {
//		service = new BoardService();
//	}
	
	static private BoardController controller = new BoardController();
	private BoardController() {
		service = new BoardService();
	}
	static public BoardController getInstance() {
		return controller;
	}
	public String boardWrite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String board_subject=  request.getParameter("board_subject");
		String board_writer = request.getParameter("board_writer");
		String board_pwd = request.getParameter("board_pwd");
		String board_content = request.getParameter("board_content");
		Board board = new Board();
		board.setBoard_subject(board_subject);
		board.setBoard_writer(board_writer);
		board.setBoard_pwd(board_pwd);
		board.setBoard_content(board_content);
		String result =  service.boardwrite(board);
		request.setAttribute("result", result);
		String path = "/result.jsp";
		return path;
	}
	public String boardReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String str_parent_no = request.getParameter("parent_no");
		int parent_no = Integer.parseInt(str_parent_no);
		String board_subject = request.getParameter("board_subject");
		String board_writer = request.getParameter("board_writer");
		String board_pwd = request.getParameter("board_pwd");
		String board_content = request.getParameter("board_content");
		
		Board board = new Board();
		board.setParent_no(parent_no);
		board.setBoard_subject(board_subject);
		board.setBoard_writer(board_writer);
		board.setBoard_pwd(board_pwd);
		board.setBoard_content(board_content);
		String result = service.boardReply(board);
		request.setAttribute("result", result);
		String path="/result.jsp";
		return path;
	}
	public String boardPwdChk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String str_board_no = request.getParameter("board_no");
		int board_no = Integer.parseInt(str_board_no);
		String board_pwd = request.getParameter("board_pwd");
		String result = service.boardPwdChk(board_no, board_pwd);
		request.setAttribute("result", result);
		String path = "/result.jsp";
		return path;
	}
	public String boardDetail(HttpServletRequest request, 
			HttpServletResponse response)  throws ServletException, IOException{
		
		String str_board_no = request.getParameter("board_no");
		
		int board_no = Integer.parseInt(str_board_no);
		try {
			Board b =  service.boardDetail(board_no);
			request.setAttribute("board", b);
			request.setAttribute("status", 1);
		}catch(NotFoundException e) {
			e.printStackTrace();
			request.setAttribute("status", -1);
		}		
		String path = "/boarddetailresult.jsp";
		return path;
	}
	public String boardList(HttpServletRequest request, 
			HttpServletResponse response)  throws ServletException, IOException{
		System.out.println("in BoardController의 boardList()");
		String currentPage = request.getParameter("currentPage");
		int intCurrentPage = 1;
		if(currentPage != null) {
			intCurrentPage = Integer.parseInt(currentPage);
		}
		try {
			PageBean<Board> pb = service.boardList(intCurrentPage);
			request.setAttribute("pb", pb);
			request.setAttribute("status", 1);
		} catch (NotFoundException e) {
			e.printStackTrace();
			request.setAttribute("status", -1);
		}		
		String path ="/boardlistresult.jsp";
		return path;
	}

}
