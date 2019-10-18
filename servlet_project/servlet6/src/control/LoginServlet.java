package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.service.CustomerService;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CustomerService service;
	public LoginServlet() {
		service = new CustomerService();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String str = service.login(id, pwd);
		
		request.setAttribute("result", str); //str설정자
		String path = "result";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
