package control;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	@RequestMapping("/a")
	//@GetMapping("/a")
	//@PostMapping("/a")
	
//	public void a() {
//		System.out.println("LoginController의 a()호출됨");
		/*
		 * default인 경우
		 * return "/WEB-INF/a.jsp"; 와 같은효과를 갖는 mvc:view
		 * 		
		 * <mvc:jsp prefix="/" suffix=".jsp" />인 경우
		 * return "/a.jsp"
		 */
//	}

	// http://localhost:8080/spring-mvc1/a?id=aaa&pwd=bbb요청
	public String a(String id, @RequestParam("pwd")String p) {
		System.out.println("LoginController의 a()호출됨");
		System.out.println("id=" + id + ", p=" + p);
		return "result.jsp"; // view이름
	}

}
