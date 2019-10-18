package control;

import java.io.IOException;
//import java.io.PrintWriter; -> ResultServlet

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.my.dao.CustomerDAO;
//import com.my.exception.NotFoundeException;
import com.my.service.CustomerService;
//import com.my.vo.Customer;

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
		
//		int status = -1; //로그인실패

//		CustomerDAO dao = new CustomerDAO();
//		try {
//			Customer c = dao.selectById(id);
//			if(c.getPwd().equals(pwd)) {
//				status = 1; //로그인성공
//			}
//		} catch(NotFoundeException e){
//			e.printStackTrace();
//		}
		
		//1.응답형식 지정하기, MIME값 활용
//		response.setContentType("text/html; charset=UTF-8");
		//2.응답출력스트림 얻기
//		PrintWriter out = response.getWriter();
		//3.응답하기
//		out.print(str);
	}

}
