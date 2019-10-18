package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.service.CustomerService;

public class DupchkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");		

		CustomerService service = new CustomerService();
		String str = service.dupchk(id);

		request.setAttribute("result", str);
		String path = "/result.jsp";
		RequestDispatcher rd = 
				request.getRequestDispatcher(path);			
		rd.forward(request, response);
	}

}
