package control;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private BoardController boardC;
	public DispatcherServlet() {
		//boardC = new BoardController();
	}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("DispatcherServlet의 service()가 호출됨!");
		
		String uri = request.getRequestURI();
		System.out.println("URI = " + uri);
		
		String contextPath = request.getContextPath();
		int index = contextPath.length();
		String servletPath = uri.substring(index);
		//System.out.println("servletPath = " + servletPath);
		
		
		String path = "/error/err404";
/*		if("/boardlist".equals(servletPath)) {
			//BoardListController boardListC = new BoardListController();
			//path = boardListC.boardList(request, response);
			path = boardC.boardList(request, response);
			//path = boardList(request, response);
			
		} else if("/boarddetail".equals(servletPath)) {
			//BoardDetailController boardListC = new BoardDetailController();
			//path = boardListC.boardDetail(request, response);
			path = boardC.boardDetail(request, response);
			//path = boardDetail(request, response);
		}
*/
		//1) dispatcher.properties파일의 프로퍼티이름과 값가져오기
		ServletContext sc = getServletContext();
		String realPath = sc.getRealPath("dispatcher.properties");
		Properties env = new Properties(); //Map계열 API
		env.load(new FileInputStream(realPath)); //프로퍼티파일 로드
												//파일에 있는 프로퍼티들이 env에 저장됨
		//2) servletPath변수값과 같은 프로퍼티찾기
		String classMethodName = env.getProperty(servletPath);
		
		//3) 프로퍼티값얻기, 분석
		int classMethodIndex = classMethodName.lastIndexOf(".");
		String className = classMethodName.substring(0, classMethodIndex); //"." 이전 : BoardController
		String methodName = classMethodName.substring(classMethodIndex+1); //"." 이후 : boardList, boardDetail
		
		//4) 클래스이름과 메서드이름 찾기 -- reflection: 객체를 통해 클래스정보를 분석해내는 기법
		try {
			Class clazz = Class.forName(className); //클래스타입의 객체생성
			
		//5) 클래스이름에 해당 객체찾기 --싱글톤패턴
			//Object obj = clazz.newInstance();
			Method getInstanceMethod = clazz.getMethod("getInstance",null);
			Object obj = getInstanceMethod.invoke(null,null); //호출:invoke(객체,매개변수)
			
		//6) 객체가 갖고있는 메서드이름에 해당하는 메서드 찾기
			Method method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			
		//7) 메서드 호출하기(invoke)
			path = (String)method.invoke(obj, request, response); //메서드 호출(invoke)
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		if(path.equals("-1")) {
			return;
		}
		//String path = "/boardlistresult.jsp";	//String path = "/boarddetailresult.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
	
}
