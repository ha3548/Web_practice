package control;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 url-pattern: /request
 */
public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		//request.setCharacterEncoding("UTF-8");//message body 영역 인코딩설정함수. post방식에만 효과가 난다.
 		
 		//http://localhost:8080/servlet5/request          아래결과 null
 		//http://localhost:8080/servlet5/request?opt=     아래결과 ""
 		//http://localhost:8080/servlet5/request?opt=test 아래결과 test
 		//http://localhost:8080/servlet5/request?opt=한글
 		
 		System.out.println(request.getParameter("opt")); 	
 		//if(request.getParameter("opt").equals("test")) {
 		if("test".equals(request.getParameter("opt"))) { 			
 		}
 		
 		//http://localhost:8080/servlet5/request
 		//http://localhost:8080/servlet5/request?c=JAVA&c=C&c=python 		
 		String[] arr = request.getParameterValues("c");
 		if(arr != null) {
 			for(String c: arr) {
 				System.out.println(c);
 			}
 		}
 		//servlet1, servlet2
 		String contextPath = request.getContextPath();
 		System.out.println(contextPath); //"/servlet5"
 		System.out.println(request.getProtocol()); // HTTP/1.1
 		System.out.println(request.getRemoteHost()); 
 		System.out.println(request.getMethod()); // GET
 		
 		System.out.println(request.getRequestURI());// /servlet5/request
 		System.out.println(request.getServerName());// localhost
 		System.out.println(request.getServerPort());// 8080
 		System.out.println(request.getServletPath());// /request
 		
 		Enumeration<String> names = request.getHeaderNames();
 		while(names.hasMoreElements()) {
 			String name = names.nextElement();
 			System.out.println("<<" + name + ">>");
 			Enumeration<String> values = request.getHeaders(name);
 			while(values.hasMoreElements()) {
 				String value = values.nextElement();
 				System.out.println(value);
 			}
 			System.out.println("--------------");
 		}
 		if(request.getHeader("user-agent").contains("Chrome")) {
 			System.out.println("크롬브라우저에서 요청했습니다.");
 		}
 		if(request.getHeader("user-agent").contains("Android")) {
 			System.out.println("안드로이드 OS에서 요청했습니다.");
 		}
 		
 		//요청전달데이터(이름:opt) 값 얻기
 		//request.getParameter("opt"); OK 
 		//request.setParameter("opt", "test"); 지원 안함
 		//request.removeParameter("opt");지원 안함
 		
 		//요청속성 설정,얻기, 제거
 		request.setAttribute("attr1", new Date());
 		
 		Integer inte = Integer.valueOf(2);
 		
 		request.setAttribute("attr2",  inte);
 		
 		request.setAttribute("attr3",  3);
 		//Autoboxing : 
 		//컴파일시에 request.setAttribute("attr3", Integer.valueOf(3));
 		//로 변경됨!
 		
 		Date dt = (Date)request.getAttribute("attr1");
 		Integer inte2 = (Integer)request.getAttribute("attr2");
 		int int3 = (Integer)request.getAttribute("attr3");
 		//AutoUnboxing
 		//컴파일시에 
 		//Integer inte3 = (Integer)request.getAttribute("attr3");
 		//int int3 = inte3.intValue();
 		//로 변경됨!
 		
 		//int int4 = (Integer)request.getAttribute("att4");
 		Integer inte4 = (Integer)request.getAttribute("att4");
 		if(inte4 != null) {
 			int int4 = inte4.intValue();
 		}
 		
 		request.removeAttribute("attr1");
 		request.removeAttribute("attr4");
 	}
}
