package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.my.service.CustomerService;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CustomerService service;
	public LoginServlet() {
		service = new CustomerService();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
/*		---아이디기억---
		String c = request.getParameter("c");
		if("save".equals(c)) { //아이디기억체크
			//1)쿠키설정
			Cookie cookie = new Cookie("saveId", request.getParameter("id"));
			//2)쿠키유효기간설정
			cookie.setMaxAge(60);//1분간 사용가능한 쿠키
			//3)쿠키응답헤더에 추가
			response.addCookie(cookie);
		} else {
			//1)쿠키설정
			Cookie cookie = new Cookie("saveId", request.getParameter("id"));
			//2)쿠키유효기간설정
			cookie.setMaxAge(0);//1분간 사용가능한 쿠키
			//3)쿠키응답헤더에 추가
			response.addCookie(cookie);
		}
*/		
		//기존세션을 지워준다(Cleaning)
		HttpSession session = request.getSession();
		session.removeAttribute("loginInfo");
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		String str = service.login(id, pwd);
		
		/*로그인성공시 HttpSession객체의 속성으로 추가*/
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(str);
			JSONObject jsonObj = (JSONObject)obj;
			if((Long)jsonObj.get("status")==1) {//로그인성공 -> 세션객체에 저장(request보다 오래살아있다)
				//HttpSession session = request.getSession();
				session.setAttribute("loginInfo", id);
			}
			
		} catch(ParseException e) {
			e.printStackTrace();
		}
		
		
		request.setAttribute("result", str);
		String path = "/result.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);//forwardq 방식으로 객체응답(응답시 사라짐)
	}

}
