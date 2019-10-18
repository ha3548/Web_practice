package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/test","/test2"}, loadOnStartup = 1, initParams = @WebInitParam(name="charset", value="UTF-8"))
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TestServlet() {
        super();
    }
    
    private String charset;
    public void init(ServletConfig sc) throws ServletException{
    	super.init(sc);
    	charset = this.getInitParameter("charset");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setContentType("text/html; charset=UTF-8");
		response.setContentType("text/html; charset=" + charset);
		PrintWriter out = response.getWriter();
		out.print("Servlet API ver 3.0부터 @어노테이션으로 서블릿매핑 가능합니다.");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
