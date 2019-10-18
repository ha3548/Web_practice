package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//페이지이동방법 1-성공
		//response.sendRedirect("http://www.google.com");
		
		//페이지이동방법 2-실패: 응답코드404 -> servlet4/http://www.google.com
		//같은 웹컨텍스트내의 다른 url로 이동
//		String path = "http://www.google.com";
//		RequestDispatcher rd = request.getRequestDispatcher(path);
//		rd.forward(request, response);
		
		//String path="login";
		//response.sendRedirect(path); //방법1
				
		//RequestDispatcher rd = request.getRequestDispatcher(path); //방법2
		//rd.forward(request, response);
		
		String opt = request.getParameter("opt");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(opt == null || opt.equals("")) {
			out.println("<form method='post' action='move'>");
			out.println("<input type='hidden' name='opt'>");
			out.println("<button type='button' id='redirect'>리다이렉트</button>");
			out.println("<button type='button' id='forward'>포워드</button>");
			out.println("<button type='button' id='include'>인클루드</button>");
			out.println("</form>");
			out.println("<script>");
			out.println("var r = document.querySelector('#redirect');");
			out.println("var f = document.querySelector('#forward');");
			out.println("var i = document.querySelector('#include');");
			out.println("var form = document.querySelector('form')");
			out.println("var hidden = document.querySelector('input[type=hidden]')");
			out.println("r.addEventListener('click', function(){hidden.value='redirect'; form.submit();});");
			out.println("f.addEventListener('click', function(){hidden.value='forward'; form.submit();});");
			out.println("i.addEventListener('click', function(){hidden.value='include'; form.submit();});");
			//out.print("r.addEventListener('click', function(){alert('리다이렉트 클릭됨!');});");
			//out.print("f.addEventListener('click', function(){alert('포워드 클릭됨!');});");
			//out.print("i.addEventListener('click', function(){alert('인클루드 클릭됨!');});");
			out.println("</script>");
			
		}

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opt = request.getParameter("opt");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.print("BEFORE");
		if(opt.equals("redirect")) {
			response.sendRedirect("login");
			
		}else if (opt.equals("forward")) {
			String path = "login";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response); // 페이지 로딩 후 다시 안돌아옴, 제어권넘김
			
		}else if (opt.equals("include")) {
			String path = "login";
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.include(request, response); // 페이지 로딩됐다가 다시 돌아옴, 제어권유지 -> BEFORE, AFTER 출력됨
		}
		out.print("AFTER");
		
	}

}
