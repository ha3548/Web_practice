package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, 
			               HttpServletResponse response) throws ServletException, IOException {
		String str = (String)request.getAttribute("result");
		
		//1.응답형식 지정하기. MIME값 활용
		response.setContentType("text/html;charset=utf-8"); //???
		
		//2.응답출력스트림 얻기
		PrintWriter out = response.getWriter();	
				
		//3.응답하기
		out.print(str);
	}

}
