<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>layout.jsp</title>
<style>
header>h1 {
  text-align: center;
}
header>nav>ul>li { 
  display:inline-block;
  margin: 10px;
}
header>nav>ul>li>a{ /*밑줄제거*/
  text-decoration: none;
}
header>nav>ul>li>a:hover{
  background-color: lightgray;
  font-weight:bold;
}
section{
  width:100%;
  height: 300px;
  /* height:50%; */
 /* position: relative*/;
}
section>div{
  float:left;
  width:70%;
  height:100%;
}
article{
 /*display: inline-block;*/
 /* float:left; */
 border:1px solid;
 /* width:300px; */ 
 height:30%; 
 overflow: auto;
 margin-top:10px; 
}
aside{
  border:1px solid;
  background-color: lightgray;
  /*width: 200px;*/
  width: 20%;
  height: 30%;
  /*display: inline-block;*/
  float:right;
}
footer {
  background-color: gray;
  color: white;
  text-align: center;
  margin-top: 200px; /*간격*/
  padding: 15px; /*안쪽여백*/
}

</style>

</head>
<body>
<header style="background-color: pink; border: 1px solid">
  <h1>KITRI</h1>
  <nav style="background-color: white;">
<!--
	<ul>
      <li><a href="#">게시판</a></li>
      <li><a href="#">공지사항</a></li>
      <li><a href="login.html">로그인</a></li>
      <li><a href="join.html">가입</a></li>
      <li><a href="display.html">display.html</a></li>
    </ul>
-->    
    <!-- menu.html 포함 rd.include() -->
    <jsp:include page="menu.jsp"/>
    
  </nav>
</header>
<section>
  <div>
    <article>
  내용1<br>  내용1<br>
  내용1<br>  내용1<br>
  내용1<br>  내용1<br>
  내용1<br>  내용1<br>
  내용1<br>  내용1<br>
 내용1<br>  내용1<br>
 내용1<br>  내용1<br>  
    </article> 
    <article>내용2</article>
  </div>
  <aside>광고</aside>
</section>
<footer>
  주소:서울시 구로구 디지털로34길 
 연락처: 02-869-8301
</footer>
</body>
</html>