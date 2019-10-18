<%@ page import="com.my.vo.Board"%>
<%@ page import="com.my.vo.PageBean"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
${requestScope.result}
<style>
div>table{
width: 100%;
height: 200px;
text-align: center;
}
div>table>td{
text-align: center;
}
tr.data>td:nth-child(4):hover{
font-weight:bold;
background-color: lightgray;
}
div.pageGroup{
width:100%;
height: 10px;
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
<script>

$(function(){
	/*페이지 넘김버튼*/
	var $prevnext = $("div.pageGroup>a");
	$prevnext.click(function(event){
		var pageNum = $(this).attr("href"); //attr: 선택한 요소(this)의 (href)속성값
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
	
	/*게시물 자세히보기*/
	var $tdArr = $("tr.data");
	$tdArr.click(function(){
		$board_no = $(this).children().eq(0).html();
		console.log($board_no);
		
		$.ajax({
			url:'${pageContext.request.contextPath}/boarddetail',
			data:'board_no='+$board_no,
			success:function(data){
				$("section").empty();
				$("section").html(data);
			}
		});
		return false;
	});
	
	/*원글쓰기 버튼*/
	var $btWriteObj = $("#btWrite");
	$btWriteObj.click(function(){
		$.ajax({
			url: '${pageContext.request.contextPath}/boardwrite.jsp',
			success:function(data){
				$("section").empty();
				$("section").html(data);
			}
		});
	});
	
});

</script>

<c:set var="status" value="${requestScope.status}"/>
<c:choose>
	<c:when test="${requestScope.status!=1}">게시물목록이없습니다.</c:when>
	<c:otherwise>
		<h1 style="text-align: center">게시판</h1>
		<button id="btWrite">글쓰기</button><br>
		
		<c:set var="pb" value="${requestScope.pb}"/>
		<c:set var="currentPage" value="${pb.currentPage}"/>
		<c:set var="maxPage" value="${pb.maxPage}"/>
		<c:set var="list" value="${pb.list}"/>
<div class="table">	
		<table>
		<tr>
		<th>글번호</th><th>제목</th><th>작성자</th><th>작성시간</th>
		</tr>
		
		<c:forEach var="b" items="${list}">
			<tr class="data">
				<td>${b.board_no}</td>
				<%-- <td>${b.parent_no}</td> --%>
				<%-- <td>${b.level}</td> --%>
				<c:choose>
				<c:when test="${b.parent_no==0}">
					<td>${fn:substring(b.board_subject, 0, 10)}</td>
				</c:when>
				<c:otherwise>
					<td><span>└ </span>${fn:substring(b.board_subject, 0, 10)}<span>(reply ${b.parent_no})</span></td>
				</c:otherwise>
				</c:choose>
				
				<td>${b.board_writer}</td>
				<td><fmt:formatDate value="${b.board_time}" pattern="yy-MM-dd hh:mm:ss"/></td>
			</tr>
		
		</c:forEach>
		</table><br>
</div>	
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






