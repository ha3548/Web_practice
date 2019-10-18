<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pageContext.jsp</title>
</head>
<body>
1. pageContext 내장객체: <%=pageContext %>
<%-- out.print(pageContext); --%>
<%-- out.print(pageContext.toString()); --%>
<%-- 클래스명@16진수해시코드 --%>
<%-- 해시코드가 다르면 서로 다른객체 --%>
<%-- 해시코드가 새로고침시마다 바뀜 -> pageContext는 요청할때마다 새로 만들어진다! --%>
<%pageContext.setAttribute("p1", "v1"); %>
<br>
2. pageContext의 attr중 이름이 p1의 값: <%=pageContext.getAttribute("p1") %>
<br>
3. request 내장객체 : <%=request %>
<%-- 해시코드가 바뀌지않음 --%>
<%-- <%request.setAttribute("r2", "v2"); %>와 같은코드--%>
<%pageContext.setAttribute("r2", "v2", PageContext.REQUEST_SCOPE); %>
<hr>
<jsp:include page="pageContext1.jsp"/>
- request의 attr중 이름이 r3의 값: <%=request.getAttribute("r3") %>
</body>
</html>

<%--
pageContext 객체가 서로 다르지만, (Request객체도 엄밀히 말하면 서로 다름)
포인터로 같은 메모리를 참조한다. 따라서 내용은 같다!
--%>