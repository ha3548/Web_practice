package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    //DB를 사용하지 않을때는 GET/POST 사용X -> Service만 사용
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//session.removeAttribute("loginInfo");
		//session.removeAttribute("savedId");
		session.invalidate();//세션 죽임
	}

}
