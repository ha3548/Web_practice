<%@page contentType="text/html; charset=UTF-8" %>

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
		case 'login.html':
			loadMenu(url, loadLogin);
			break;
		case 'join.html':
			loadMenu(url, loadJoin);
			break;
		
		case 'display.html':
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
     <li><a href="#">게시판</a></li>
     <li><a href="#">공지사항</a></li>
     <li><a href="login.html">로그인</a></li>
     <li><a href="join.html">가입</a></li>
     <li><a href="display.html">display.html</a></li>
</ul>
