package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.service.CustomerService;
import com.my.vo.Customer;
import com.my.vo.Post;

public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerService service;
	public JoinServlet() {
		service = new CustomerService();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String buildingno = request.getParameter("buildingno");
		String addr = request.getParameter("addr2");
		Customer c = new Customer();
		System.out.println(id + ":" + pwd + ":" + name + ":" + buildingno + ":" + addr);
		c.setId(id);
		c.setPwd(pwd);
		c.setName(name);
		Post post = new Post();
		post.setBuildingno(buildingno);
		c.setPost(post);
		c.setAddr(addr);
		
		String str = service.join(c);		
		request.setAttribute("result", str);
		String path = "/result.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
