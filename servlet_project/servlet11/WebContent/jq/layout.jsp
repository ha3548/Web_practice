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
  font-weight:bold;
}
section{
  width:100%;
  height: 400px;
}
section>div{
  float:left;
  width:70%;
  height:100%;
}
article{
 border:1px solid;
 height:50%; 
 overflow: auto;/*자동스크롤*/
 margin-top:10px; 
}
aside{/*광고팝업*/
  border:1px solid;
  background-color: lightgray;
  width: 20%;
  height: 50%;
  /*display: inline-block;*/
  float:right;
  margin-top:10px;
}
footer {
  background-color: gray;
  color: white;
  text-align: center;
  margin-top: 130px; /*간격*/
  padding: 15px; /*안쪽여백*/
}

</style>
</head>
<body>

<header style="background-color: pink; border: 1px solid">
  <h1>KITRI</h1>
    <nav style="background-color: white;">
      <jsp:include page="menu.jsp"/>
    </nav>
</header>

<section>
  <div>
    <article>
	  내용1<br>내용1<br>내용1<br>내용1<br>내용1<br>내용1<br>내용1<br>
	  내용1<br>내용1<br>내용1<br>내용1<br>내용1<br>내용1<br>내용1<br> 
    </article> 
    <article>내용2</article>
  </div>
  <aside>
    광고<br>
  <a href="http://www.kitri.re.kr/kitri/news/01edu_it_list.web">
  §KIT§리㏇<br>웹♧자바♣개~발~자~과정<br>♨수☆당☆금♨ $ 40 $ 만원<br>
        ☞지금★바로★전화주세요☜<br>☎ 02-869-8301 ☎
  </a>
  </aside>
</section>

<footer>
  주소: 서울시 구로구 디지털로 34길 코오롱싸이언스밸리1차
</footer>

</body>
</html>