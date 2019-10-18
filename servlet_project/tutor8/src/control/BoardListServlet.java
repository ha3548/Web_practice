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
import com.my.vo.PageBean;

public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService service;
	public BoardListServlet() {
		service = new BoardService();
	}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		request.getRequestURL();
		String path ="/boardlistresult.jsp";
		RequestDispatcher rd = 
				request.getRequestDispatcher(path);
		rd.forward(request, response);
	
	}
}
