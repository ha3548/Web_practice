package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("FirstServlet의 doPost()호출됨");
		
		String id = request.getParameter("id");
		System.out.println(id);
		
		String pwd = request.getParameter("pwd");
		System.out.println(pwd);
		
		int status=-1;
		
		// 이부분 DB짜기
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		try {
          String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
          String DB_USER = "c##ora_user";
          String DB_PW = "bong";
          Class.forName("oracle.jdbc.driver.OracleDriver");
          conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
       } catch (Exception e) {
          e.printStackTrace();
       }
        
       String SQL = "select * from customer where id = ?";
       
       try {
          stmt = conn.prepareStatement(SQL);
          stmt.setString(1, id);
          rs = stmt.executeQuery();
          if (rs.next()) {
             if(pwd.equals(rs.getString("pwd"))){
                status = 1;	//아이디중복
             }
          }

       } catch (Exception e) {
          e.printStackTrace();
       } finally {
          try {
             if (stmt != null && !stmt.isClosed())
                stmt.close();
             if (rs != null && !rs.isClosed())
                stmt.close();
          } catch (SQLException e) {
             e.printStackTrace();
          }
       }

		
		
		
		//1.응답형식 지정하기, MIME값 활용
		response.setContentType("text/html; charset=UTF-8");//charset설정해야 한글 안깨짐!
		//2.응답출력스트림 얻기
		PrintWriter out = response.getWriter();
		//3.응답하기
		String str = "{\"status\":" + status + ", \"id\": \""+id +"\"}";
		out.print(str);
		//out.print(id + "님 로그인 성공!");
		/*out.print("<h1>"+id+"님 로그인 성공! </h1>");
		if(cArr!=null) {
			for(String c:cArr) {
				out.print(c);//println은 응답'내용'에 줄바꿈 포함, 브라우저에서 줄바꿈 -> <br>
				out.print("<br>");
			}
		}
		
		out.print("<script>alert('로그인성공'); location.href='jq/layout.html'; </script>");*/
	}

}
