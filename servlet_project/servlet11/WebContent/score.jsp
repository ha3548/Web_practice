<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>score.jsp</title>
</head>
<body>
<% String star = request.getParameter("star"); %>
<%! int starInt;%>
<% starInt += Integer.parseInt(star);%>
<%! int cnt; //외부 인스턴스변수로 선언 %>

<h1><%=star %>점을 선택하셨습니다.</h1>
참여인원 <%=++cnt %>명<br>
평점 <%=(starInt*100/cnt)/100f%> (총점<%= starInt%>)<br>

</body>
</html>