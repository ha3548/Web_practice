<%@ page import="com.my.vo.Board"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<style>

</style>


<%
int status = (Integer)request.getAttribute("status");
if(status!=1){
%>	게시물목록이없습니다.
<%} else{%>
<h1 style="text-align: center">게시판</h1><br>

<div>
<%
List<Board> list = (List)request.getAttribute("boardlist");
%>
<table>
<tr>
<th>게시물번호</th><th>부모글번호</th><th>중첩레벨</th><th>제목</th><th>작성자</th><th>작성시간</th>
</tr>
<%
for(Board b:list){
%>
<tr>
	<td><%=b.getBoard_no() %></td>
	<td><%=b.getParent_no() %></td>
	<td><%=b.getLevel() %></td>
	<td><%=b.getBoard_subject() %></td>
	<td><%=b.getBoard_writer() %></td>
	<td><%=b.getBoard_time() %></td>
<%}%>
</tr>
</table><br>
<%}%>
</div>