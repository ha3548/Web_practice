<%@ page import="com.my.vo.Board"%>
<%@ page import="com.my.vo.PageBean"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
${requestScope.result}
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
			url:'${pageContext.request.contextPath}/boardlist',
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

<c:set var="status" value="${requestScope.status}"/>
<c:choose>
	<c:when test="${requestScope.status!=1}">게시물목록이없습니다.</c:when>
	<c:otherwise>
		<h1 style="text-align: center">게시판</h1><br>
		
		<c:set var="pb" value="${requestScope.pb}"/>
		<c:set var="currentPage" value="${pb.currentPage}"/>
		<c:set var="maxPage" value="${pb.maxPage}"/>
		<c:set var="list" value="${pb.list}"/>
		
		<table>
		<tr>
		<th>게시물번호</th><th>부모글번호</th><th>중첩레벨</th><th>제목</th><th>작성자</th><th>작성시간</th>
		</tr>
		
		<c:forEach var="b" items="${list}">
			<tr class="data">
				<td>${b.board_no}</td>
				<td>${b.parent_no}</td>
				<td>${b.level}</td>
				<td>${b.board_subject}</td>
				<td>${b.board_writer}</td>
				<td>${b.board_time}</td>
			</tr>
		
		</c:forEach>
		</table><br>
	</c:otherwise>
</c:choose>


<div class="pageGroup">

<c:set var="startPage" value="${pb.startPage}"/>
<c:set var="endPage" value="${pb.endPage}"/>

<c:if test="${startPage>1}">
	<a href="${startPage-1}"><span>[Prev]</span></a>
</c:if>

<c:forEach var="i" begin="${startPage}" end="${endPage}">
	<c:choose>
		<c:when test="${i == currentPage}">
			<span class="underline">${i}</span>
		</c:when>
		<c:when test="${i != currentPage}">
			<a href="${i}"><span>${i}</span></a>&nbsp;&nbsp;
		</c:when>
	</c:choose>
</c:forEach>

<c:if test="${endPage < maxPage}">
	<a href="${endPage+1}"><span>[Next]</span></a>
</c:if>

</div>






