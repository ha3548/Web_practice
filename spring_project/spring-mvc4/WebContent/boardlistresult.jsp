<%@page import="com.my.vo.PageBean"%>
<%@page import="com.my.vo.Board"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="contextPath.jsp" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style>
div.board{ /*게시물 목록 영역*/
  width:70%;
  height:auto;
}

div.board>div.tr{ /*행 */
  width:100%;
  clear:both;
  float:left;
}
div.board>div.origin{ /*원글 행 */
  border-top:1px solid;
  margin: 3px;
}

div.board>div.rep{ /*답글 행*/
  border-top:1px dotted;
}
/*답글행의 글번호에 마우스올리면 JS에서 tooltip_rep스타일이 추가됨*/
div.board>div.rep>div.tooltip_rep{
 position: relative;
}
/*답글행의 글번호에 tooltip_rep스타일이 추가되면 글번호의 내용
  <div class="board_no" data="2번 글의 답글">
 &nbsp;&nbsp;3
  </div>
  이 
  아래처럼 바뀜
  <div class="board_no" data="2번 글의 답글">
 &nbsp;&nbsp;3 <2번 글의 답글>
</div>
*/
div.board>div.rep>div.tooltip_rep:after{
   content: attr(data);
    position: absolute;
	top: 100%; 
	left: 50%;    
	width: 100%;
    background-color: rgba(0, 0, 0, 0.8);
	color: #FFFFFF;
	font-size: 8px;
	z-index: 9999;
}

div.board>div.tr:not(:first-child):hover{ /*첫번째 행을 제외한 모든 행의 마우스올림*/ 
  background-color: yellow;
  font-weight:bold;
}
div.board_no, div.board_subject, div.board_writer, div.board_time{
  width:30%;
  float:left;
}

div.board_no{
  width:10%;
  clear:both;
}
div.parent_no{ /*부모글번호는 감춤*/
  display:none;
}



/*span.underline{
  text-decoration: underline;
  font-weight: bold;
  cursor: pointer;
}*/
/* ex: 
<div class="pageGroup">
   <ul>
      <li><a href="5"><span>PREV</span></a></li>
      <li><a href="6"><span>6</span></a></li>
      :
      <li><a href="9"><span>9</span></a></li>
   </ul>
</div>
*/      
div.pageGroup{ /*페이지 그룹 */
    text-align: center !important;
}
div.pageGroup>ul{ 
    text-align: center !important;
    display:inline-block;
    padding-left: 0;
    margin: 20px 0;
    border-radius: 2px;
}
div.pageGroup>ul>li{
    display: inline;
}
</style>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 --><script>
$(function(){
	var $board_no = $("div.board>div.rep>div.board_no");
	$board_no.hover(function(){
		$(this).addClass("tooltip_rep");
	}, function(){
		$(this).removeClass("tooltip_rep");
	});
	
    var $aArr = $("div.pageGroup > ul> li> a");	
    $aArr.click(function(){
    	var pageNum = $(this).attr("href"); //1,2,3,4,5
    	$.ajax({
    		url: "${contextPath}/boardlist",
    		data:'currentPage='+pageNum,
    		success:function(data){
    		    $("section").empty();
    		    $("section").html(data);    		
    		}    		
    	});//end ajax  
    	return false;
    });//end click
    
    //행이 클릭되었을때
    $("div.board>div.tr:not(:first-child)").click(function(){
    	var board_no = $(this).find(".board_no>span").html();
    /* 	alert(board_no); */
    	$.ajax({
    		url:"${contextPath}/boarddetail",
    		data:'board_no='+board_no,
    		success: function(data){
    			$("section").empty();
    			$("section").html(data);
    		}    		
    	});
    	return false;
    });
    
    /*글쓰기 버튼객체 찾기*/
    var $btWriteObj = $("#btWrite");
    $btWriteObj.click(function(){
    	$.ajax({
    		url: '${contextPath}/boardwrite.jsp',
    		success:function(data){
    			$("section").empty();
    			$("section").html(data);
    		}
    	});
    });
    
});//end $(function(){
</script>
<c:choose>
  <c:when test="${requestScope.status != 1}">게시물 목록이 없습니다</c:when>
  <c:otherwise>
     <c:set var="pb" value="${requestScope.pb}"/>
     <c:set var="currentPage" value="${pb.currentPage}"/>
     <c:set var="maxPage" value="${pb.maxPage}"/>
     <c:set var="list" value="${pb.list}"/>
<h2>게시판</h2>
<div class="board"><button id="btWrite">글쓰기</button></div>
<div class="board">
  <div class="tr"><div class="board_no">글번호</div> <div class="parent_no">부모글번호</div> <div class="board_subject">제목</div>  <div class="board_writer">작성자</div>  <div class="board_time">작성시간</div></div>  

  <c:forEach var="b" items="${list}">  
  <c:choose>
  <c:when test="${b.level == 1}">
  <div class="tr origin">
    <div class="board_no"><span>${b.board_no}</span></div>
  </c:when>
  <c:otherwise>
  <div class="tr rep">
    <%--답글인 글의 글번호에 툴팁에서 보여줄 data라는 사용자정의 속성을 추가 --%> 
    <div class="board_no" data="${b.parent_no}번글의 답글">
      <%--level만큼 들여쓰기 --%>
      <c:forEach begin="1" end="${b.level-1}">&nbsp;&nbsp;</c:forEach>       
<span>${b.board_no}</span>
    </div>
  </c:otherwise>
  </c:choose>
      
    <div class="parent_no">${b.parent_no}</div>
<%--     <div class="board_subject">${b.board_subject}</div> --%>
    <div class="board_subject">${fn:substring(b.board_subject, 0, 5)}</div>
    <div class="board_writer">${b.board_writer}</div>
<%--     <div class="board_time">${b.board_time}</div> --%>
    <div class="board_time"><fmt:formatDate 
                                value="${b.board_time}"
                                pattern="yy-MM-dd hh:mm:ss"/></div>
  </div> 
  </c:forEach>
  
</div>
<div class="pageGroup">
 <ul>
 <c:set var="startPage" value="${pb.startPage}"/>
 <c:set var="endPage" value="${pb.endPage}"/>
 <c:if test="${startPage > 1}">
   <li><a href="${startPage-1}"><span>PREV</span></a></li>
 </c:if> 
 
 <c:forEach var="i" begin="${startPage}"  end="${endPage}" >
    <c:choose>
      <c:when test="${i == currentPage}">
        <li><span>${i}</span></li>
      </c:when>
      <c:otherwise>
        <li><a href="${i}"><span>${i}</span></a></li>
      </c:otherwise>
    </c:choose>
 </c:forEach>
 
 <c:if test="${endPage < pb.maxPage}">
 <li><a href="${endPage+1}"><span>NEXT</span></a></li>
 </c:if> 
 </ul>
</div>
  </c:otherwise>
</c:choose>
<%System.out.println("end boardlistresult.jsp");%>