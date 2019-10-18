<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
header>h1{
  text-align: center; 
}
header>nav>ul>li { 
  display:inline-block;
  margin: 10px;
}
header>nav>ul>li>a{ 
  text-decoration: none;
}
header>nav>ul>li>a:hover{
  background-color: yellow;
  font-weight:bold;
}

</style>
<script>
var loadLogin = function(data){
	$("section").empty();
	$("section").html(data);
	//$("section>div>article:nth-child(1)").html(data);
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
var loadLogout = function(data){
	location.href="${pageContext.request.contextPath}/jq/layout.jsp";
};
var loadMenu = function(u, callback){
	$.ajax({
		url: u,
		method: 'GET',
		success: function(data){
			callback(data);
		}
	});
	return false;
};
$(function(){	
	var $menuArr = $("header>nav>ul>li>a");
	$menuArr.click(function(){		
	  var url = $(this).attr('href');
	  switch(url){
	  case '${pageContext.request.contextPath}/boardlist':
		  loadMenu(url, loadBoardList);
		  break;
	  case '${pageContext.request.contextPath}/jq/login.jsp':
		  loadMenu(url, loadLogin);
		  break;
	  case '${pageContext.request.contextPath}/jq/join.jsp':
		  loadMenu(url, loadJoin);
		  break;
	  case '${pageContext.request.contextPath}/logout':
		  loadMenu(url, loadLogout);
	  case '${pageContext.request.contextPath}/jq/display.html':
		  loadMenu(url, function(data){
			$("section").empty();
			$("section").html(data);
		  });
	  }
	  return false; //기본이벤트핸들러 막기, //이벤트전달 중지
	});
});
</script>
<ul>
  <li><a href='${pageContext.request.contextPath}/boardlist'>게시판</a></li>
  <li><a href="#">공지사항</a></li>
  <%--로그인 안된경우에 보일 메뉴 --%>
  <c:choose>
  <c:when test="${empty sessionScope.loginInfo}">
  <li><a href='${pageContext.request.contextPath}/jq/login.jsp'>로그인</a></li>
  <li><a href='${pageContext.request.contextPath}/jq/join.jsp'>가입</a></li>
  </c:when>
  <%--로그인된 경우에 보일 메뉴 --%>
  <c:otherwise>
  <li><a href="${pageContext.request.contextPath}/logout">로그아웃</a>
  </c:otherwise>
  </c:choose>
  <li><a href='${pageContext.request.contextPath}/jq/display.html'>display.html</a></li>      
</ul>


