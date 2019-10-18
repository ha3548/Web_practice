package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.exception.NotFoundException;
import com.my.service.BoardService;
import com.my.vo.Board;

public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService service;
	public BoardDetailServlet() {
		service = new BoardService();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
