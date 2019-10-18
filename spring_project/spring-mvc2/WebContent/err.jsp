<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isErrorPage="true" %>    
<%-- Exception e = (Exception)request.getAttribute("exception"); --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>err.jsp</title>
</head>
<body>
<h1><%--=e.getMessage() --%>
<%=exception.getMessage() %>
</h1>
</body>
</html>