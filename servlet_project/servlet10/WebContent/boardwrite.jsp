<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.my.vo.Board"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
${requestScope.result}
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
$(function(){
	/*작성 버튼*/
	$("form").submit(function(){
		$.ajax({
			url:'${pageContext.request.contextPath}/boardwrite',
			method:'post',
			data:$(this).serialize(),
			success:function(data){
				alert(data);
			}
		});
		return false;
	});
});
</script>

<c:set var="b" value="${requestScope.board}"/>

<h2>게시글 쓰기</h2>
<form>
  <input type="text" name="board_subject" placeholder="글제목"><br>
  <input type="text" name="board_writer" placeholder="작성자"><br>
  <input type="password" name="board_pwd" placeholder="글비밀번호"><br>
  <textarea rows="3" cols="20" name="board_content"></textarea><br>
  <button type="submit">작성</button>
  <button type="reset">CLEAR</button>
</form>