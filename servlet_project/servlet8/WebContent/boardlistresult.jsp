<%@ page import="com.my.vo.Board"%>
<%@ page import="com.my.vo.PageBean"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%String contextPath = request.getContextPath(); %>
<style>
table{
width: 100%;
height: 200px;
}
td{
text-align: center;
}
.data:hover{
font-weight:bold;
}
div{
width:100%;
text-align: center;
color: blue;
}
span.underline{
text-decoration: underline;
font-weight: bold;
}
span:hover{
text-decoration: underline;
font-weight:bold;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
$(function(){

<%--	var $spanArr = $("div.pageGroup>span");
	$spanArr.click(function(){
		var pageNum = $(this).html();	//1,2,3,4,5
		$.ajax({
			url:'<%=contextPath%>/boardlist',
			data:'currentPage='+pageNum,
			success:function(data){
				$("section").empty();
				$("section").html(data);
			}
		});
	});
--%>

	var $prevnext = $("div.pageGroup>a");
	$prevnext.click(function(event){
		var pageNum = $(this).attr("href");
		$.ajax({
			url:'<%=contextPath%>/boardlist',
			data:'currentPage='+pageNum,
			success:function(data){
				$("section").empty();
				$("section").html(data);
			}
		});
		return false;
	});
	
});

</script>

<%
int status = (Integer)request.getAttribute("status");
if(status!=1){
%>	게시물목록이없습니다.
<%	return;
}%>
<h1 style="text-align: center">게시판</h1><br>
<%
PageBean<Board> pb = (PageBean)request.getAttribute("pb");
int currentPage = pb.getCurrentPage();
int maxPage = pb.getMaxPage();
List<Board> list = pb.getList();
//List<Board> list = (List)request.getAttribute("boardlist");
%>
<table>
<tr>
<th>게시물번호</th><th>부모글번호</th><th>중첩레벨</th><th>제목</th><th>작성자</th><th>작성시간</th>
</tr>
<%
for(Board b:list){
%>
<tr class="data">
	<td><%=b.getBoard_no() %></td>
	<td><%=b.getParent_no() %></td>
	<td><%=b.getLevel() %></td>
	<td><%=b.getBoard_subject() %></td>
	<td><%=b.getBoard_writer() %></td>
	<td><%=b.getBoard_time() %></td>
<%}%>
</tr>
</table><br>

<div class="pageGroup">
<%
int startPage = pb.getStartPage();
int endPage = pb.getEndPage();

if(startPage>1){
	%><a href="<%=startPage-1%>"><span>[Prev]</span></a><%
}

for(int i=startPage;i<=endPage;i++){
	if(i==currentPage){%>
		<span class="underline"><%=i%></span><%	
	} else {%>
		<a href="<%=i%>"><span><%=i%></span></a>&nbsp;&nbsp;<%
	}
}

if(endPage<pb.getMaxPage()){
	%><a href="<%=endPage+1%>"><span>[Next]</span></a><%
}
%>

</div>







