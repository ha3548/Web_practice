package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public FirstServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, 
			             HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FirstServlet의 doGet()호출됨");
		String id = request.getParameter("id");
		System.out.println(id);
		String pwd = request.getParameter("pwd");
		System.out.println(pwd);
		String []cArr = request.getParameterValues("c");
		if(cArr != null) { //1개이상 선택되었을때
			for(int i=0; i<cArr.length; i++) {
				System.out.println(cArr[i]);
			}
		}
		//1.응답형식 지정하기. MIME값 활용
		response.setContentType("text/html;charset=utf-8"); //???
		//2.응답출력스트림 얻기
		PrintWriter out = response.getWriter();
		//3.응답하기
//		out.print(id + "님 로그인 성공!");
		String str = "{\"status\":1, \"id\": \"" + id +"\"}"; 
		out.print(str);
//		out.print("<h1>" + id + "님 로그인 성공!</h1>");
//		if(cArr != null) {
//			for(String c:cArr) {
//			  out.print(c);
//			  out.print("<br>");			  
//			}
//		}
//		out.print("<script>alert('로그인성공'); location.href='jq/layout.html';</script>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FirstServlet의 doPost()호출됨");
	}

}
