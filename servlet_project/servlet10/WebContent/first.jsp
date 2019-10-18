<%@page import="java.io.FileNotFoundException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.my.vo.Customer" %>
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
	  	  			<%-- 정적포함: 완전히 포함시킬때 사용 --%>
	  	  		</li>
	  	  		<li>taglib directive</li>
	  	  	</ol>
	  	  </li>
	  	  <li>action tag
	  	  	<ol>
	  	  		<li>standard action tag<br>
	  	  			-include tag: RequestDispatcher의 include()와 같음<br>
	  	  			<jsp:include page="jq/menu.jsp"/>
	  	  			<%-- 동적포함: 자바소스코드 실행결과값만 가져올때 많이사용 --%>
	  	  			-forward tag:  RequestDispatcher의 forward()와 같음,
	  	  							다른페이지로 아예 이동해버려서 잘 사용X<br>
	  	  			<%-- <jsp:forward page="jq/menu.jsp"/> --%>
	  	  			-useBean tag: 아래있는 코드와 똑같은 기능을 한다.<br>
	  	  			<jsp:useBean class="com.my.vo.Customer" id="c" scope="request"/>
	  	  			<%--com.my.vo.Customer c = (Customer)request.getAttribute("c");
	  	  				if(c==null){
	  	  					c=new Customer();
	  	  					request.setAttribute("c", c);
	  	  				}
	  	  			--%>
	  	  			-setProperty / getProperty tag
	  	  			<jsp:setProperty name="c" property="pwd" value="p3"/>
	  	  			<%--<%c.setPwd("p3"); %> --%>
	  	  			<jsp:getProperty name="c" property="pwd"/>
	  	  			<%--<%=c.getPwd() %> --%>
	  	  			
	  	  			<%-- set/get은 확장성이 떨어짐 => EL,JSTL
	  	  			<jsp:getProperty name="c" property="Post"/> -> 
	  	  			<%=c.getPost().getZipcode() %> --%>
	  	  		</li>
	  	  		<li>custom action tag</li>
	  	  	</ol>
	  	  </li>
	  	</ol>
	  	<div>
	  	  Default Object(내장객체) : .java파일의 _jspService()내부에 미리 선언되어있는 매개변수와 지역변수들
	  	  <ul>
	  	    <li>request - HttpServletRequest</li>
	  	    <li>response - HttpServletResponse</li>
	  	    <li>page - Object : 현재객체</li>
	  	    <li>out - JSPWriter : 응답출력스트림(PrintWriter + BufferedWriter)</li>
	  	    <li>config - ServletConfig</li>
	  	    <li>session - HttpSession : 세션트래킹(서버사이드 상태정보 유지기술)</li>
	  	    <li>application - ServletContext : 웹어플리케이션의 정보(servlet api version/reslPath..)를 담고있는 객체
	  	    	웹컨텍스트내의 Servlet과 JSP들 사이의 공유객체로 사용됨</li>
	  	    <li>pageContext - PageContext : 현재사용중인 JSP의 정보(request/response/out..)를 담고있는 객체</li>
	  	    <li>exception : page지시자의 isErrorPage속성값이 true일 때만 만들어짐 ex)err.jsp</li>
	  	  </ul>
	  	</div>
	  </li>
	</ul>
</div>
</body>
</html>