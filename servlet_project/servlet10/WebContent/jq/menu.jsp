<%@page contentType="text/html; charset=UTF-8" %>
<%
String contextPath = request.getContextPath();
%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
var loadLogin = function(data){
	//$("section>div>article:nth-child(1)").html(data);
	$("section").empty();
	$("section").html(data);
};

var loadJoin = function(data){
	//$("section>div>article:nth-child(2)").html(data);
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
		
		case '${pageContext.request.contextPath}/jq/login.html':
			loadMenu(url, loadLogin);
			break;
		case '${pageContext.request.contextPath}/jq/join.html':
			loadMenu(url, loadJoin);
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
     <li><a href='${pageContext.request.contextPath}/jq/login.html'>로그인</a></li>
     <li><a href='${pageContext.request.contextPath}/jq/join.html'>가입</a></li>
     <li><a href='${pageContext.request.contextPath}/jq/display.html'>display.html</a></li>
</ul>
