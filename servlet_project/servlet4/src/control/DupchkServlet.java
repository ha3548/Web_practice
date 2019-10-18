package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.my.dao.CustomerDAO;
//import com.my.exception.NotFoundeException;
import com.my.service.CustomerService;
//import com.my.vo.Customer;

public class DupchkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DupchkServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DupchkServlet의 doPost()호출됨");
		String id = request.getParameter("id");
		System.out.println(id);
		
		CustomerService service = new CustomerService();
		String str = service.dupchk(id);
		
//		int status = -1; // 중복:1, 중복안됨:-1
//       
//		CustomerDAO dao = new CustomerDAO();
//		try {
//			Customer c = dao.selectById(id);
//			if(c.getId().equals(id)) {
//				status = 1; //아이디중복
//			}
//		} catch(NotFoundeException e){
//			e.printStackTrace();
//		}
		
		//1.응답형식 지정하기, MIME값 활용
		response.setContentType("text/html; charset=UTF-8");//charset설정해야 한글 안깨짐!
		//2.응답출력스트림 얻기
		PrintWriter out = response.getWriter();
		//3.응답하기
		//String str = "{\"status\":" + status + ", \"id\": \""+id +"\"}";
		out.print(str);
	}

}
