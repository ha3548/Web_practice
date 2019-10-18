<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
${requestScope.result}
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
var loadLogin = function(data){
	//$("section>div>article:nth-child(1)").html(data);
	$("section").empty();
	$("section").html(data);
};

var loadLogout = function(data){
	location.href="${pageContext.request.contextPath}/jq/layout.jsp";
};

var loadJoin = function(data){
	$("section").empty();
	$("section").html(data);
};

var loadBoardList = function(data){
	$("section").empty();
	$("section").html(data);
};

var loadMenu = function(u, callback){
	$.ajax({
		url: u,
		method: 'GET',
		success: function(data){
			console.log("OK",data);
			callback(data);
		}
	});
};

$(function(){
	//var menuArr = document.querySelectorAll("header>nav>ul>li>a");
	var $menuArr = $("header>nav>ul>li>a");
	$menuArr.click(function(event){
		var url = $(this).attr('href'); //attr: 선택한 요소(this)의 (href)속성값
		
		switch(url){
		case '${pageContext.request.contextPath}/boardlist' :
			loadMenu(url,loadBoardList);
		
		case '${pageContext.request.contextPath}/jq/login.jsp':
			loadMenu(url, loadLogin);
			break;
		case '${pageContext.request.contextPath}/jq/join.html':
			loadMenu(url, loadJoin);
			break;
		case '${pageContext.request.contextPath}/logout':
			loadMenu(url, loadLogout);
			break;
		case '${pageContext.request.contextPath}/jq/display.html':
			loadMenu(url, function(data){
				$("section").empty();
				$("section").html(data);
			});
		}
		return false; //기본이벤트핸들러 막기, 이벤트전달 중지
	});
});
</script>

<ul>
     <li><a href='${pageContext.request.contextPath}/boardlist'>게시판</a></li>
     <li><a href="#">공지사항</a></li>
     
     <c:choose>
	     <c:when test="${empty sessionScope.loginInfo}">
		     <li><a href='${pageContext.request.contextPath}/jq/login.jsp'>로그인</a></li>
		     <li><a href='${pageContext.request.contextPath}/jq/join.html'>가입</a></li>
		 </c:when>
		 <c:otherwise>
		     <li><a href='${pageContext.request.contextPath}/logout'>로그아웃</a></li>
	     </c:otherwise>
     </c:choose>
     
     <li><a href='${pageContext.request.contextPath}/jq/display.html'>display</a></li>
</ul>
