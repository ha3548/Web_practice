package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.service.BoardService;
import com.my.vo.Board;

public class BoardReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService service;
	public BoardReplyServlet() {
		service = new BoardService();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
	
}
