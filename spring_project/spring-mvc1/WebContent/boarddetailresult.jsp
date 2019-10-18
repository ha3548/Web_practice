<%@ page contentType="text/html; charset=UTF-8"%>
<%--boarddetailresult.jsp--%>
<%@include file="contextPath.jsp" %>
<style>
div.boardDetail{
 font-size: 14px;
 height: auto;
}
label:after{
 content: ": "
} 
span.detail{
/* white-space: normal;*//*기본값. 공백을 여러개 넣어도 공백1개만 표시. 글이 길어지면 자동줄바꿈됨!*/
/* white-space: nowrap; */ /*공백 여러개 넣어도 공백1개만 표시, 글이 길어져도 자동줄바꿈 안됨!*/
 /* white-space: pre-wrap; */ /*공백(줄바꿈,들여쓰기,spacebar)을 있는 그대로 표시, 글이 길어지면 자동 줄바꿈됨!*/
 white-space: pre-line; /*공백 여러개 넣어도 공백1개만 표시,  글이 길어지면 자동줄바꿈됨!*/ 
 
}
div.buttonGroup{
 height: auto;
 text-align: center !important;
 margin-top: 30px;
}
.btn{
 display: inline-block;
 text-align: center;
 vertical-align: middle;
 cursor: pointer;
 border: 1px solid transparent;
 border-radius: 4px;
 padding: 0px 18px;
 letter-spacing: -1px;
 font-size: 14px;
}
div.boardPwdChk{
 margin:10px;
 height:auto;
 display:none;
 
}
</style>
<script>
$(function(){
	$("button.reply").click(function(){
		var board_no = ${board.board_no};
		$.ajax({
			url : '${contextPath}/boardreply.jsp',
			method: 'get',
			data: 'board_no='+board_no,
			success:function(data){
				$("section").empty();
				$("section").html(data);
			}
		});
		return false;
	});
	
	$("button.modify").click(function(){
		var $boardPwdChkObj = $("div.boardPwdChk");
		console.log($boardPwdChkObj.html());
		$boardPwdChkObj.find("input[name=board_pwd]").val('');
		$boardPwdChkObj.show();
	});
	
	$("button.remove").click(function(){
		var $boardPwdChkObj = $("div.boardPwdChk");
		$boardPwdChkObj.find("input[name=board_pwd]").val('');
		$boardPwdChkObj.show();
	});
	$("div.boardPwdChk>button").click(function(){
		var board_no = ${board.board_no};
		var board_pwd = $("div.boardPwdChk>input[name=board_pwd]").val();
		$.ajax({
			url: '${contextPath}/boardpwdchk',
			method:'post',
			data:'board_no='+board_no + '&board_pwd='+board_pwd,
			success:function(data){
				var jsonObj = JSON.parse(data);
				if(jsonObj.status == 1){
					alert("글비밀번호 인증 성공!");
				}else{
					alert("글비밀번호 인증 실패!");
				}
				$("div.boardPwdChk").hide();
			}
		});
	});
});
</script>
<div class="boardDetail"> 
<c:choose>
<c:when test="${requestScope.status != 1}">
    해당 게시글[글번호:${param.board_no}번]의 상세정보가 없습니다.
</c:when>
<c:otherwise>
<c:set var="board" value="${requestScope.board}"/>

<div class="board_no"><label>게시글 번호 </label><span class="detail">${board.board_no}</span></div>
<c:if test="${board.parent_no>0}">
  <div class="parent_no"><label>부모글 번호 </label><span class="detail">${board.parent_no}</span></div>
</c:if>  
<div class="board_subject"><label>글 제목 </label><span class="detail">${board.board_subject}</span></div>
<div class="board_time"><label>작성 시간 </label><span class="detail">${board.board_time}</span></div>
<div class="board_content"><label>글 내용 </label><span class="detail">${board.board_content}</span></div>
</c:otherwise>
</c:choose>
</div>
<div class="buttonGroup">
  <button class="btn modify">수정</button>
  <button class="btn remove">삭제</button>
  <button class="btn reply">답글쓰기</button>
</div>
<div class="boardPwdChk">
 글 비밀번호:<input type="password" name="board_pwd" placeholder="글비밀번호를 입력하세요">
 <button>확인</button>
</div>
<form action="${pageContext.request.contextPath}/test.jsp">
<textarea name="ta"></textarea>
<button>테스트</button>
</form>
