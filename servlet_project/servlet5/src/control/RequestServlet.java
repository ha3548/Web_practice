package control;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// http://localhost:8080/servlet5/request //결과:null
		// http://localhost:8080/servlet5/request?opt= //결과:(빈문자열)
		// http://localhost:8080/servlet5/request?opt=test //결과:test
		// http://localhost:8080/servlet5/request?opt=한글 //결과:한글

		System.out.println(request.getParameter("opt"));
		// if(request.getParameter("opt").equals("test")) {
		if ("test".equals(request.getParameter("opt"))) { // NullPointerException을 피할수있다!

		}
		// http://localhost:8080/servlet5/request?c=JAVA&c=C&c=python
		String arr[] = request.getParameterValues("c");
		if (arr != null) {
			for (String c : arr) {
				System.out.println(c);
			}
		}

		String contextPath = request.getContextPath();
		System.out.println(contextPath); // /servlet5
		System.out.println(request.getProtocol()); // HTTP/1.1
		// System.out.println(request.getRemoteHost()); // 0:0:0:0:0:0:0:1
		System.out.println(request.getMethod()); // GET
		System.out.println(request.getRequestURI()); // /servlet5/request
		System.out.println(request.getServerName()); // localhost
		System.out.println(request.getServerPort()); // 8080
		System.out.println(request.getServletPath()); // /request

		Enumeration<String> names = request.getHeaderNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			System.out.println("<<"+name+">>");
			
			Enumeration<String> values = request.getHeaders(name);
			while(values.hasMoreElements()) {
				String value = values.nextElement();
				System.out.println(value);
			}
			System.out.println("------------------------");
		}
		if(request.getHeader("user-agent").contains("Chrome")) {
			System.out.println("크롬브라우저에서 요청했습니다.");
		}
		if(request.getHeader("user-agent").contains("Android")) {
			System.out.println("안드로이드에서 요청했습니다.");
		}
		
		//요청전달데이터(이름:opt)값 얻기
		request.getParameter("opt");
		//request.setParameter("opt","test"); X -> setAttribute
		//request.removeParameter("opt"); X -> removeAttribute
		
		//요청속성 설정,얻기,제거
		request.setAttribute("attr1", new Date());
		//request.setAttribute("attr2", new Integer(2)); //얜 안되나?
		Integer inte = Integer.valueOf(2);
		request.setAttribute("attr1", inte);
		request.setAttribute("attr3", 3); //AutoBoxing
		//컴파일시 request.setAttribute("attr3", Integer.valueOf(3));
/*				
		Date dt = (Date)request.getAttribute("attr1");
		Integer inte2 = (Integer)request.getAttribute("attr2");
		int inte3 = (Integer)request.getAttribute("attr3"); //AutoUnBoxing
		//컴파일시
		//Integer inte3 = (Integer)request.getAttribute("attr3");
		//int inte3 = inte3.intValue(); 로 변경됨!
		
		Integer inte4 = (Integer)request.getAttribute("attr4");
		if(inte4!=null) {
			int int4 = inte4.intValue();
		}
		
		request.removeAttribute("attr1");
		request.removeAttribute("attr4");
*/
	}

}
