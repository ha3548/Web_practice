<%@ page import="com.my.vo.Board"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
${requestScope.result}
<style>
div.button>button{
border: thick;
background-color: pink;
}
div.info{
height: 150px;
}
div.button{
height: 50px;
}
div.test{
display: none;
height: 50px;
}
#msg{
color: red;
font-size: x-small;
display: none;
}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
$(function(){
	/*답글쓰기버튼*/
	var $replyObj = $("#reply");
	$replyObj.click(function(){
		$board_no = $("div>span").html();
		console.log($board_no);
		
		$.ajax({
			url: '${pageContext.request.contextPath}/boardreply.jsp',
			data:'board_no='+$board_no,
			success:function(data){
				$("section").empty();
				$("section").html(data);
			}
		});
	});
	
	/*수정버튼*/
	var $btnObj = $("#edit_btn");
	var $submitObj = $("div.test");
	$btnObj.click(function(){
		$submitObj.css('display','inline-block');
	});
	
	/*비번>확인버튼*/
	var real_pwd = $("#real_pwd").val();
	
	$("form").submit(function(){
		var input_pwd = $("#input_pwd").val();
		
			console.log(real_pwd);
			console.log(input_pwd);
			
			if(input_pwd == real_pwd){
				//alert("비밀번호 일치")
				$("#msg").css("display","none");
			} else {
				//alert("비밀번호 불일치")
				$("#msg").css("display","block");
			}
			return false;
	});
	
});
</script>

<c:set var="status" value="${requestScope.status}"/>
<c:choose>
  <c:when test="${requestScope.status!=1}">게시물목록이없습니다.</c:when>
  <c:otherwise>
    <c:set var="b" value="${requestScope.board}"/>
		
  <h3>게시글</h3>
  
  <div class="info">
	글번호 : <span>${b.board_no}</span>
	<br><%--답글인경우--%>
	<c:if test="${b.parent_no!=0}">
	부모글번호 : <span>${b.parent_no}</span><br>
	</c:if>
	제목 : <span>${b.board_subject}</span>
	<br>
	작성자 : <span>${b.board_writer}</span>
	<br>
	작성시간 : <span>${b.board_time}</span>
	<br>
	글내용 : <span>${b.board_content}</span>
	<br>
	<input type="hidden" id="real_pwd" value="${b.board_pwd}">
  </div>
  <br>
  <div class="button">
	  <button id="edit_btn">수정</button>
	  <button id="delete">삭제</button>
	  <button id="reply">답글쓰기</button>
  </div>
  
  <div class="test">
  <form>
    <input type="password" placeholder="비밀번호를 입력하세요" id="input_pwd">
    <button type="submit">확인</button>
  </form>
  <span id="msg">비밀번호가 일치하지 않습니다.</span>
  </div>
  
  
  </c:otherwise>
</c:choose>
