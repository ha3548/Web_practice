package control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.my.service.BoardService;
import com.my.service.CustomerService;
import com.my.vo.Customer;
import com.my.vo.Post;

@Controller
public class CustomerController {
	@Autowired
	private CustomerService service;
	
	@PostMapping("/login")
	public ModelAndView login(String id, String pwd, HttpSession session) {
		//HttpSession session = request.getSession();
		session.removeAttribute("loginInfo");
		
		String str = service.login(id, pwd);
		//로그인성공시 HttpSession객체의 속성으로 추가
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(str);
			JSONObject jsonObj = (JSONObject)obj;
			if((Long)jsonObj.get("status") == 1) {//로그인 성공!
				session.setAttribute("loginInfo", id);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		ModelAndView mnv = new ModelAndView();
		mnv.addObject("result", str);		//request.setAttribute("result", str);
		mnv.setViewName("/result.jsp");		//String path = "/result.jsp";
		return mnv;							//return path;
	}
/*  public String login(String id, String pwd, HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute("loginInfo");
		
		String str = service.login(id, pwd);
		//로그인성공시 HttpSession객체의 속성으로 추가
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(str);
			JSONObject jsonObj = (JSONObject)obj;
			if((Long)jsonObj.get("status") == 1) {//로그인 성공!
				session.setAttribute("loginInfo", id);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		request.setAttribute("result", str);
		String path = "/result.jsp";
		return path;
	}*/
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request, HttpSession session){
		session.invalidate();
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", 9999); //의미없는 값
		String str = jsonObj.toString();
		
		ModelAndView mnv = new ModelAndView();
		mnv.addObject("result", str);		//request.setAttribute("result", str);
		mnv.setViewName("/result.jsp");		//String path = "/result.jsp";
		return mnv;							//return path;
	}
/*	public String logout(HttpServletRequest request, HttpSession session) throws ServletException, IOException {
		//HttpSession session = request.getSession();
		session.invalidate();		
		//return "-1";
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", 9999); //의미없는 값
		request.setAttribute("result", jsonObj.toString());
		String path = "/result.jsp"; //SpringMVC에서는 뷰가 반드시 필요하다!
		return path;
	}*/
	
	@PostMapping("/dupchk")
	public ModelAndView dupchk(String id){
		String str = service.dupchk(id);

		ModelAndView mnv = new ModelAndView();
		mnv.addObject("result", str);		//request.setAttribute("result", str);
		mnv.setViewName("/result.jsp");		//String path = "/result.jsp";
		return mnv;							//return path;
	}
/*	public String dupchk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");		

		//CustomerService service = new CustomerService();
		String str = service.dupchk(id);

		request.setAttribute("result", str);
		String path = "/result.jsp";
		return path;
	}*/
	
	@PostMapping("/join")
	public ModelAndView join(@ModelAttribute("c") Customer c, String buildingno, @RequestParam(required = false, defaultValue = "0") int age, @RequestParam("addr2") String addr) {
		System.out.println("age=" + age);	//선택전달 매개변수
		//System.out.println(c.getId() + ":" + c.getPwd() + ":" + c.getName());
		Post post = new Post();
		post.setBuildingno(buildingno);
		c.setPost(post);
		c.setAddr(addr);
		String str = service.join(c);
		
		ModelAndView mnv = new ModelAndView();
		mnv.addObject("result", str);	//request.setAttribute("result", str);
		mnv.setViewName("/result.jsp");	//String path = "/result.jsp";
		return mnv;						//return path;
	}
	
/*	public String join(String id, String pwd, String name, String buildingno, String addr2) {
		Customer c = new Customer();
		System.out.println(id + ":" + pwd + ":" + name + ":" + buildingno + ":" + addr2);
		c.setId(id);
		c.setPwd(pwd);
		c.setName(name);
		Post post = new Post();
		post.setBuildingno(buildingno);
		c.setPost(post);
		c.setAddr(addr2);
		
		String str = service.join(c);		

		String path = "/result.jsp";
		return path;
	}
*/
	
/*	public String join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		return path;
	}*/
	
}
