package control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.my.service.BoardService;
import com.my.service.CustomerService;
import com.my.vo.Customer;
import com.my.vo.Post;

@RestController	//@Controller + @ResponseBody
public class CustomerController {
	@Autowired
	private CustomerService service;
	
	@PostMapping("/login")
	@ResponseBody	//return값이 뷰 이름이 아니라 응답내용이다!!는 뜻
	//POJO타입으로 사용
	//1. 반환값을 Jackson라이브러리를 활용하여 JSON문자열로 변호나시킨다.
	//2. 응답형식을 application/json타입으로 설정한다.
	public Map<String, Object> login(String id, String pwd, HttpSession session) {
		session.removeAttribute("loginInfo");
		
		Map<String, Object> map = service.login(id, pwd);
		if((Integer)map.get("status")==1) {
			session.setAttribute("loginInfo", id);
		}
		return map;
		
		/*String str = service.login(id, pwd);
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
		
		return str;*/
	}

	@RequestMapping("/logout")
	@ResponseBody
	public void logout(HttpServletRequest request, HttpSession session){
		session.invalidate();
	}
	
	@PostMapping("/dupchk")
	@ResponseBody
	public Map<String, Object> dupchk(String id){
		return service.dupchk(id);
	}

	@PostMapping("/join")
	@ResponseBody
	public Map<String, Object> join(@ModelAttribute("c") Customer c, String buildingno, @RequestParam("addr2") String addr) {
		Post post = new Post();
		post.setBuildingno(buildingno);
		c.setPost(post);
		c.setAddr(addr);
		return service.join(c);
	}
}
