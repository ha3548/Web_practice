package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.exception.NotFoundException;
import com.my.service.ZipService;


public class SearchZipServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ZipService service;
    public SearchZipServlet() {
    	service = new ZipService();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String doro = request.getParameter("doro");
		String str = null;
		try {
			str = service.searchZip(doro);
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		request.setAttribute("result", str); //str설정자
		String path = "result";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}