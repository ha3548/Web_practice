package control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.my.service.CustomerService;
import com.my.vo.Customer;
import com.my.vo.Post;

public class CustomerController {
	private CustomerService service;
	static private CustomerController controller = new CustomerController();
	private CustomerController() {
		service = new CustomerService();
	}
	static public CustomerController getInstance() {
		return controller;
	}
	
	public String login(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute("loginInfo");
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		String str = service.login(id, pwd);
		
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(str);
			JSONObject jsonObj = (JSONObject)obj;
			if((Long)jsonObj.get("status")==1) {//로그인성공 -> 세션객체에 저장
				session.setAttribute("loginInfo", id);
			}
			
		} catch(ParseException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("result", str);
		String path = "/result.jsp";
		return path;
	}
	
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();

		return "-1";
	}
	
	public String dupchk(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String str = service.dupchk(id);
		
		request.setAttribute("result", str);
		String path = "/result.jsp";
		return path;
	}
	
	public String join(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String buildingno = request.getParameter("buildingno");
		String addr = request.getParameter("addr2");
		
		Customer c = new Customer();
		c.setId(id);
		c.setPwd(pwd);
		c.setName(name);
		c.setAddr(addr);
		Post post = new Post();
		c.setPost(post);
		post.setBuildingno(buildingno);
		
		String str = service.join(c);
		request.setAttribute("result", str);
		
		String path = "/result.jsp";
		return path;
	}
}
