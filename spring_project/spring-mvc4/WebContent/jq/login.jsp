<%@page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta charset="UTF-8">
<script>
//window.addEventListener("load",function(){
//jQuery(document).ready(function(){	
//$(document).ready(function(){
$(function(){
	/* 기억된 ID 찾기 */
	var savedId = localStorage.getItem("savedId");
	if(savedId != null && savedId != ''){
		$("input[name=id]").val(savedId);
	}
	
	$("button[type=button]").click(function(){
		//alert("가입버튼이 클릭되었습니다");
		//menu중 가입메뉴객체를 찾기
		var $menuArr = $("header > nav > ul > li > a");
		for(var i=0; i<$menuArr.length; i++){
			var aObj = $menuArr[i];
			if($(aObj).attr('href') == '${contextPath}/jq/join.jsp'){
				//alert($(aObj).html()); //가입
				//click이벤트 강제발생시키기
				$(aObj).trigger('click');
				break;
			}
		}
	});
	//$("button[type=submit]").click(function(){ //1	
	//	alert("submit버튼에서 click이벤트 발생");
		/* $("form").attr('action', '../a.jsp');
		$("form").submit(); */
		//return false;
		//기본이벤트 핸들러가 할 일 : form의 submit이벤트를 강제 발생시킨다.
	//});	
	$("form").submit(function(){ 
	    /*아이디 기억*/
	    //dom트리에서 name속성이 c인 객체찾기
	    $cObj = $("input[name=c]");
	    
	    //$cObj객체가 선택된 상태인가 확인
	    if($cObj.prop("checked")){ //선택
	       //id값을 storage에 기억	
	       var id = $("input[name=id]").val();
	       localStorage.setItem("savedId", id);	    	
	    }else{ //해제
	       //id값을 storage에서 제거
	       localStorage.removeItem("savedId");
	    }	    
	    /*---------*/
	    $.ajax({
          url:	'${contextPath}/login',
          method : 'post',
          data: $('form').serialize(),
          success: //function(data){
        	 // var jsonObj = JSON.parse(data);
        	 function(jsonObj){
        		 
        	  var msg = jsonObj.id + "님 로그인 ";
              if(jsonObj.status == 1){
            	  msg += "성공";
            	  alert(msg);
            	  location.href='${contextPath}';/* "${contextPath}/jq/layout.jsp"; */
              }else {
            	  msg += "실패";
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
<%
/*------------------*/
/* String idValue = "";
//1)요청헤더의 쿠키들 얻기
Cookie[] cArr = request.getCookies();
if(cArr != null){
	//2)쿠키중 이름이 savedId인 쿠키찾기, 쿠키값을 id입력란의 value속성으로 추가한다
    for(Cookie c: cArr){
    	if(c.getName().equals("savedId")){
    		idValue = c.getValue();
    		break;
    	}
    }
}
 */
 /*-------------*/
%>
ID : <input type="text" name="id" placeholder="아이디를 입력하세요" 
           <%--  value="<%=idValue%>" --%>
            required><br>
비밀번호:<input type="password" name="pwd" placeholder="비밀번호를 입력하세요"
            required><br>
            
<%-- <input type="checkbox" name="c" value="1">ONE&nbsp;&nbsp;
<input type="checkbox" name="c" value="2">TWO&nbsp;&nbsp;
<input type="checkbox" name="c" value="3">THREE<br>  --%>
<input type="checkbox" name="c" value="save" checked>아이디 기억<br>          

<button type="submit"><spring:message code="login"/></button>
<button type="button"><spring:message code="join"/></button>
</form>
</body>
</html>