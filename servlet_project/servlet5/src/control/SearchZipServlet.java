package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SearchZipServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String doro = request.getParameter("doro");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//응답내용
//		[
//		
//		{"zipcode":"12345",
//		 "addrdoro":"주소1",
//		 "addrzibun":"주소2"
//		},
//		{"zipcode":"12345",
//		 "addrdoro":"주소1",
//		 "addrzibun":"주소2"
//		}		
//		]
		String result="["; 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "c##ora_user";
			String password = "bong";
			con = DriverManager.getConnection(url, user, password);
			String SQL = "SELECT zipcode,  \r\n" + 
			        "buildingno, "+
					"sido||' ' \r\n" + 
					"|| sigungu ||NVL2(sigungu,' ', '')\r\n" + 
					"|| eupmyun ||NVL2(eupmyun,' ', '')\r\n" + 
					"|| doro ||' ' \r\n" + 
					"|| building1\r\n" + 
					"|| DECODE(building2,'0', '', '-'||building2) ||' ' \r\n" + 
					"|| '('|| dong || ri || DECODE(building, '', '', ',' ||building) ||')'\r\n" + 
					" AS addrdoro" + //"||chr(13)||chr(10) \r\n" +
					","+
					"sido ||' ' \r\n" + 
					"|| sigungu ||NVL2(sigungu,' ', '')\r\n" + 
					"|| eupmyun ||NVL2(eupmyun,' ', '')\r\n" + 
					"|| dong || ri ||' ' ||  zibun1 || DECODE(zibun2, '0', '',  '-'|| zibun2)    ||' ' || DECODE(building, '', '', '(' ||building ||')')     "
					+ " AS addrzibun  \r\n" + 
					
					"FROM post\r\n" + 
					"WHERE (doro || ' ' || building1 || DECODE(building2,'0', '', '-'||building2)) LIKE ? ";
			pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, "%" + doro + "%");
			rs  = pstmt.executeQuery();
			int cnt = 0;
			while(rs.next()) { 
				String zipcode = rs.getString("zipcode");
				String addrdoro = rs.getString("addrdoro");
				String addrzibun = rs.getString("addrzibun");
				String buildingno = rs.getString("buildingno");
				if(cnt != 0) {
					result += ",";
				}
				
				result += "{\"zipcode\":\""+ zipcode +"\",\"addrdoro\":\""+ addrdoro+"\",\"addrzibun\":\"" + addrzibun + "\", \"buildingno\":\""+ buildingno+"\"}";
				cnt++;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) 
				try {
					rs.close();
				}catch(SQLException e) {}
			if(pstmt != null) 
				try {
				pstmt.close();
				}catch(SQLException e) {}
			if(con != null) 
				try {
					con.close();
				}catch(SQLException e) {}
		}
		result +="]";
		System.out.println(result);
		//1.응답형식 지정하기. MIME값 활용
		//response.setContentType("text/html;charset=utf-8");
		response.setContentType("application/json;charset=utf-8");
		//2.응답출력스트림 얻기
		PrintWriter out = response.getWriter();
		//3.응답하기
		out.print(result); 
		
	}

}