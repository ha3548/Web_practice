<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
Connection con = null;
PreparedStatement pstmt = null;	
ResultSet rs = null;
try {
	Class.forName("oracle.jdbc.driver.OracleDriver");
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "c##ora_user";
	String password = "yeo";
	con = DriverManager.getConnection(url, user, password);
	String selectSQL = "SELECT * FROM  test";
	pstmt = con.prepareStatement(selectSQL);//SQL구문 송신			
	rs = pstmt.executeQuery();//SQL구문 실행	
	String data = "";
	while(rs.next()){
		System.out.println(rs.getString(1));
%>
<span style="white-space:normal">normal>>><%=rs.getString(1) %><<<----</span><br>
<span style="white-space:pre-wrap;">pre-wrap>>><%=rs.getString(1) %><<<----</span><br>		
<span style="white-space: pre-line;">pre-line>>><%=rs.getString(1) %><<<----</span><br>				
<%	}
%>
<%}catch(Exception e){
	e.printStackTrace();
}finally{
	pstmt.close();
	con.close();
}
%>
<%
String ta = request.getParameter("ta");
if(ta != null && !ta.equals("")){
	System.out.println(ta);
try {
	Class.forName("oracle.jdbc.driver.OracleDriver");
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "c##ora_user";
	String password = "yeo";
	con = DriverManager.getConnection(url, user, password);
	String insertSQL = "INSERT INTO test(a) VALUES (?)";
	pstmt = con.prepareStatement(insertSQL);//SQL구문 송신			
	pstmt.setString(1, request.getParameter("ta"));
	pstmt.executeUpdate();//SQL구문 실행			
}catch(Exception e){
	e.printStackTrace();
}
}//end if
%>

</body>
</html>