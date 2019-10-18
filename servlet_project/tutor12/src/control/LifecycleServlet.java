package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LifecycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//String charset = "UTF-8";
	String charset;
	String pm;
    public LifecycleServlet() {
        super();
        System.out.println("LifecycleServlet생성자 호출됨");
    }
    //서블릿객체생성후 자동호출될 메서드
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		System.out.println("LifecycleServlet.init()호출됨");
		ServletContext ctx = this.getServletContext();
        System.out.println(ctx.getMajorVersion()+"." + ctx.getMinorVersion());
        String path = "images/icon-search.png";
        System.out.println(ctx.getRealPath(path));
                     //web.xml의 <servlet><init-param>
        charset = this.getInitParameter("charset");
        
             //web.xml의 <context-param><init-param>
        pm = ctx.getInitParameter("pm");        
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LifecycleServlet.doGet()호출됨");	
		//String charset = "UTF-8";
		response.setContentType("text/html;charset="+ charset);
		PrintWriter out = response.getWriter();
		out.print("프로젝트 매니져 : " + pm);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LifecycleServlet.doPost()호출됨");		
	}
	
	//서블릿객체소멸시 자동호출될 메서드
	public void destroy() {
		System.out.println("LifecycleServlet.destroy()호출됨");
	}
}
