<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>score.jsp</title>
<style>
span:hover{
 position: relative;
}
span:hover:after {
	content: attr(data);
    position: absolute;
	bottom: 100%; 
	left: 0;    
	width: 100%;
	
    background-color: rgba(0, 0, 0, 0.8);
	color: #FFFFFF;
	font-size: 8px;
	z-index: 9999;
}
</style>
</head>
<body>
<%String star = request.getParameter("star");
if(star == null){%>
    별점을 선택하세요
<%return;
  }
%>
<h1><%=star%>점을 선택하셨습니다.</h1>
<%-- int cnt=0;//_jspService()내부의 지역변수로 선언 --%>
<%! int cnt; //_jspService()외부의 인스턴스변수로 선언  %>
<%! int sum; %>
<%  sum += Integer.parseInt(star); %>
참여인원   <%= ++cnt %>명<br>
평점 : <%= (float)sum/cnt%>점 (<span data="총점"><%=sum %></span> / <span data="참여인원"><%=cnt %></span>)
</body>
</html>




