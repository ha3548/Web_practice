package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.service.CustomerService;

public class DupchkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CustomerService service;
    public DupchkServlet() {
    	service = new CustomerService();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String str = service.dupchk(id);
		
		request.setAttribute("result", str); //str설정자
		String path = "/result.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);

	}

}
