<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
1. 포함된 pageContext1.jsp에서 pageContext내장객체: <%=pageContext %>
<br>
2. pageContext의 attr중 이름이 p1의 값: <%=pageContext.getAttribute("p1")%>
<%-- pageContext가 서로 다르기때문에 null 출력 --%>
<br>
3. request내장객체 : <%=request %>
<br>
- request의 attr중 이름이 r2의 값: <%=request.getAttribute("r2") %>
<br>
<%request.setAttribute("r3", "v3"); %>
