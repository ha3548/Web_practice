<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pageContext.jsp</title>
</head>
<body>
pageContext내장객체: <%=pageContext %><%-- out.print(pageContext); --%>
                                    <%-- out.print(pageContext.toString()) --%>
                                    <%--            클래스명@16진수해시코드 --%>
<%pageContext.setAttribute("p1", "v1"); %>
<br>
request내장객체: <%=request %>
<%-- <%request.setAttribute("r2", "v2"); %> --%>
<%pageContext.setAttribute("r2", "v2", PageContext.REQUEST_SCOPE); %>
<hr>
<jsp:include page="pageContext1.jsp"/> 
<hr>
pageContext의 attr중 이름이 p1의 값:<%=pageContext.getAttribute("p1")%>
<br>
request의 attr중 이름이 r3의 값:<%=request.getAttribute("r3") %>
</body>
</html>