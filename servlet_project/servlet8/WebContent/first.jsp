<%@page import="java.io.FileNotFoundException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>first.jsp</title>
<script>
	var now = new Date();
	//alert(now);//순서: JSP(서버쪽)날짜계산 -> JavaScript날짜계산
</script>
</head>
<body>
첫번째 JSP입니다.
<div>
	<h3>JSP구성요소</h3>
	<ul>
	  <li>html요소
	  	<!-- 클라이언트에게 무조건 응답된다-->
	  </li>
	  <li>jsp요소
	  	<%-- 클라이언트에게 응답X, jsp주석  권장--%>
	  	<ol>
	  	  <li>scripting
	  	    <ul>
	  	      <li>scriptlet: _jspService()내부 저장될 자바구문(요청시마다 처리될 구문)<br>
		  	    <% int num;//_jspService()내부 지역변수, 초기화 필수!
					num=15;
		  	      	//System.out.println(num);
		  	      	out.print(num);
		  	    %><br>
		  	    <% out.print(request.getParameter("opt"));
		  	    %><br>
		  	    <% out.print(new java.util.Date()); 
		  	  	%>
		  	  </li>
	  	      <li>expression: _jspService()내부 저장될 자바구문(out.print 자동호출됨)<br>
	  	      	<%=num %><br>
	  	      	<%=request.getParameter("opt") %><br>
	  	      	<%=new java.util.Date() %><br>
	  	      </li>
	  	      <li>declaration: _jspService()외부에 저장될 자바구문<br>
	  	      <%!
	  	      	void m(){}//out.println("mmm");
	  	      	int num;//_jspService()외부 인스턴스 변수, 초기값=0
	  	      %>
	  	      	지역변수 num=<%=num %>, 인스턴스변수 num=<%=this.num %>
	  	      </li>
	  	    </ul>
	  	  </li>
	  	  <li>directive
	  	  	<ol>
	  	  		<li>page directive: .java만들어질때 필요한 정보를 설정<br>
	  	  			contentType, import, buffer, errorPage, isErrorPage 속성
	  	  		<%@page contentType="text/html; charset=UTF-8" %>
	  	  		<%@page import="java.util.List" %>
	  	  		<%@page import="java.util.Map" %>
	  	  		<%@page import="java.util.Calendar" %>
	  	  		<%@page import="java.io.FileInputStream" %>
	  	  		<%@page import="java.io.FileNotFoundException" %>
	  	  		<%@page buffer="1000kb" %>
	  	  		<%List<String> list;
	  	  		  Map<String, Object> map;
	  	  		  Calendar calendar;
	  	  		%>
	  	  		<%--
	  	  			for(int i=1; i<=1000;i++){
	  	  				if(i%100==1){%><br>
	  	  			<%}%><%=i %>,
	  	  			<%}
	  	  		--%>
	  	  		<%@page errorPage="err.jsp" %>
	  	  		<%--
	  	  			FileInputStream fis = null;
	  	  			String fileName="a.txt";
	  	  			fis = new FileInputStream(fileName);
	  	  		--%>
	  	  		</li>
	  	  		<li>include directive
	  	  			<%@include file="jq/menu.jsp" %>
	  	  		</li>
	  	  		<li>taglib directive</li>
	  	  	</ol>
	  	  </li>
	  	  <li>action tag
	  	  	<ol>
	  	  		<li>standard action tag
	  	  			-include tag
	  	  			<jsp:include page="jq/menu.jsp"/>
	  	  		</li>
	  	  		<li>custom action tag
	  	  			
	  	  		</li>
	  	  	</ol>
	  	  </li>
	  	</ol>
	  </li>
	</ul>
</div>
</body>
</html>