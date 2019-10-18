<%@page import="com.my.vo.Post"%>
<%@page import="com.my.vo.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>el.jsp</title>
</head>
<body>
<%-- http://localhost:8080/servlet9/el.jsp?foo=hello --%>
<%-- http://localhost:8080/servlet9/el.jsp --%>

요청전달데이터<br>
[Expression] foo : <%=request.getParameter("foo") %><br>
<%-- <%
String a = request.getParameter("foo");
out.print(a);
%> --%>
[Expression Language] foo : ${param.foo}<br>
<%-- null값을 빈문자열""로 반환 --%>
<hr>
<%
Customer c = new Customer();
c.setId("id1");
c.setPwd("p1");
Post p = new Post();
p.setBuildingno("987654321");
c.setPost(p);
request.setAttribute("c", c);
%>
요청속성<br>
[Expression] 고객의 아이디: <%=((Customer)request.getAttribute("c")).getId() %><br>
[Expression Language] 고객의 아이디: ${requestScope.c.id}<br>
[Expression] 고객의 건물관리번호: <%=((Customer)request.getAttribute("c")).getPost().getBuildingno() %><br>
[Expression Language] 고객의 건물관리번호: ${requestScope.c.post.buildingno}<br>
<hr>
웹콘텍스트경로<br>
[Expression] : <%=request.getContextPath() %><br>
[Expression Language] : ${pageContext.request.contextPath}<br>
<hr>
이미지경로<br>
[Expression] : <%=request.getRealPath("images/movie_image.jpg") %><br>
<%--[Expression Language] : ${pageContext.servletContext.}<br> -> RealPath를 사용할수없다! --%>
[Expression Language] : 제공안함<hr>
<c:param name="s">
${param.s}
</c:param><hr>

</body>
</html>