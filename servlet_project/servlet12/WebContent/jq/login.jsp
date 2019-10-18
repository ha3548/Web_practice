<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="UTF-8">
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
<!-- 다른 html내부에서 작동할때는 라이브러리 쓰지 않음 -->
<script>
$(function(){
	/*기억된 ID찾기*/
	var savedId = localStorage.getItem("savedId");
	if(savedId != null && savedId !=''){
		$("input[name=id]").val(savedId)
	}
	

	$("button[type=button]").click(function(){
		//alert("가입버튼이 클릭되었습니다.");
		//menu중 '가입'객체 찾기
		var $menuArr = $("header > nav > ul > li > a");
		for(var i=0; i<$menuArr.length; i++){
			var aObj = $menuArr[i];
			if($(aObj).attr('href')=='join.html'){
				//alert($(aObj).html()); //'가입'
				$(aObj).trigger('click'); //이벤트 강제발생
				break;
			}
		}
	});
	
	//기본이벤트핸들러 할일: form의 submit이벤트 강제발생
	//		$("form").submit(function(){ -> 무한반복 ERROR!
	/* $("button[type=submit]").click(function(){
		alert("submit버튼에서 submit이벤트 발생");
		$("form").attr('action','../a.jsp');
		$("form").submit();
		return false;
	}); */
	
	
	//기본이벤트핸들러 할일: form의 action속성값의 URL요청
	//action속성값이 없으면, 현재사용중인 URL요청
	$("form").submit(function(){
		
		/*아이디 기억*/
		
		$cObj = $("input[name=c]");
		if($cObj.prop("checked")){//선택됨
			var id = $("input[name=id]").val();
			localStorage.setItem("savedId",id);
		} else {//선택안됨
			//id값을 storage에서 제거
			localStorage.removeItem("savedId");
		}
		
		
		$.ajax({
			url: '../login',
			method: 'post',
			data: $("form").serialize(),
			success: function(data){
				var jsonObj = JSON.parse(data);
				var msg = jsonObj.id + "님 로그인 ";

				if(jsonObj.status == 1){
					msg +="성공";
					alert(msg);
					location.href="layout.jsp";
				} else if(jsonObj.status == -1){
					msg +="실패";
					alert(msg);
				}
				
			}
		});
		
		return false;
	});
	
});
</script>
</head>
<body>
<form>

<%--
String idValue = "";
//1)요청헤더의 쿠키를 얻기
Cookie cArr[] = request.getCookies();
if(cArr!=null){
//2)쿠키중 이름이 saveId인 쿠키찾기, 쿠키값을 id입력란의 value속성으로 추가한다
	for(Cookie c: cArr){
		if(c.getName().equals("saveId")){
			idValue = c.getValue();
			break;
		}
	}
}
--%>

	ID:	<input type="text" name="id" placeholder="아이디를 압력하세요" <%--value="<%=idValue%>"--%> required><br>
	비밀번호: 	<input type="password" name="pwd" placeholder="비밀번호를 압력하세요" required><br>
	<!--<input type="checkbox" name="c" value=1>ONE&nbsp;&nbsp;
	<input type="checkbox" name="c" value=2>TWO&nbsp;&nbsp;
	<input type="checkbox" name="c" value=3>THREE<br>-->
	<input type="checkbox" name="c" value="save" checked>아이디기억<br>
	
	<button type="submit">로그인</button><!-- form안에서 버튼타입을 설정하지 않으면 submit으로 설정됨 -->
	<button type="button">가입</button>
</form>
</body>
</html>