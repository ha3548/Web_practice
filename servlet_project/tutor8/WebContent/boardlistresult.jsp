<%@page import="com.my.vo.PageBean"%>
<%@page import="com.my.vo.Board"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%String contextPath = request.getContextPath();%>
<style>
div.board{ /*게시물 목록 영역*/
  width:70%;
  height:auto;
}

div.board>div.tr{ /*행 */
  width:100%;
  clear:both;
  float:left;
}
div.board>div.origin{ /*원글 행 */
  border-top:1px solid;
  margin: 3px;
}

div.board>div.rep{ /*답글 행*/
  border-top:1px dotted;
}
/*답글행의 글번호에 마우스올리면 JS에서 tooltip_rep스타일이 추가됨*/
div.board>div.rep>div.tooltip_rep{
 position: relative;
}
/*답글행의 글번호에 tooltip_rep스타일이 추가되면 글번호의 내용
  <div class="board_no" data="2번 글의 답글">
 &nbsp;&nbsp;3
  </div>
  이 
  아래처럼 바뀜
  <div class="board_no" data="2번 글의 답글">
 &nbsp;&nbsp;3 <2번 글의 답글>
</div>
*/
div.board>div.rep>div.tooltip_rep:after{
   content: attr(data);
    position: absolute;
	top: 100%; 
	left: 50%;    
	width: 100%;
    background-color: rgba(0, 0, 0, 0.8);
	color: #FFFFFF;
	font-size: 8px;
	z-index: 9999;
}

div.board>div.tr:not(:first-child):hover{ /*첫번째 행을 제외한 모든 행의 마우스올림*/ 
  background-color: yellow;
  font-weight:bold;
}
div.board_no, div.board_subject, div.board_writer, div.board_time{
  width:30%;
  float:left;
}

div.board_no{
  width:10%;
  clear:both;
}
div.parent_no{ /*부모글번호는 감춤*/
  display:none;
}



/*span.underline{
  text-decoration: underline;
  font-weight: bold;
  cursor: pointer;
}*/
/* ex: 
<div class="pageGroup">
   <ul>
      <li><a href="5"><span>PREV</span></a></li>
      <li><a href="6"><span>6</span></a></li>
      :
      <li><a href="9"><span>9</span></a></li>
   </ul>
</div>
*/      
div.pageGroup{ /*페이지 그룹 */
    text-align: center !important;
}
div.pageGroup>ul{ 
    text-align: center !important;
    display:inline-block;
    padding-left: 0;
    margin: 20px 0;
    border-radius: 2px;
}
div.pageGroup>ul>li{
    display: inline;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
$(function(){
	var $board_no = $("div.board>div.rep>div.board_no");
	$board_no.hover(function(){
		$(this).addClass("tooltip_rep");
	}, function(){
		$(this).removeClass("tooltip_rep");
	});
	
	
    //var $spanArr = $("div.pageGroup > span");
    //$spanArr.click(function(){
    //	var pageNum = $(this).html(); //1,2,3,4,5
    var $aArr = $("div.pageGroup > ul> li> a");	
    $aArr.click(function(){
    	var pageNum = $(this).attr("href"); //1,2,3,4,5
    	$.ajax({
    		url: "<%=contextPath%>/boardlist",
    		data:'currentPage='+pageNum,
    		success:function(data){
    		    $("section").empty();
    		    $("section").html(data);    		
    		}    		
    	});//end ajax  
    	return false;
    });//end click
});//end $(function(){
</script>
<%
int status = (Integer)request.getAttribute("status");
if(status != 1){
%>게시물 목록이 없습니다	
<%
  return;
}	
PageBean pb = (PageBean)request.getAttribute("pb");
int currentPage = pb.getCurrentPage();
int maxPage = pb.getMaxPage();
List<Board> list = pb.getList();
%><h2>게시판</h2>
<div class="board">
<div class="tr">
  <div class="board_no">글번호</div>
  <div class="parent_no">부모글번호</div>
  <div class="board_subject">제목</div>
  <div class="board_writer">작성자</div>
  <div class="board_time">작성시간</div>
</div>  
<%
 for(Board b: list){
%>
<div class="tr <%=b.getLevel()==1?"origin":"rep"%>">

<div class="board_no" data="<%=b.getParent_no()%>번 글의 답글">
 <%for(int i=1; i<b.getLevel(); i++){ %>&nbsp;&nbsp;<%} %>
<%=b.getBoard_no() %>
</div>
<div class="parent_no"><%=b.getParent_no() %></div>
<div class="board_subject"><%=b.getBoard_subject() %></div>
<div class="board_writer"><%=b.getBoard_writer()%></div>
<div class="board_time"><%=b.getBoard_time()%></div>
</div>  
<%}//end for
%>
</div>
<div class="pageGroup">
 <ul>
<%
int startPage = pb.getStartPage();
int endPage = pb.getEndPage();
if(startPage > 1){%>
 <li><a href="<%=startPage-1%>"><span>PREV</span></a></li>
<%}
for(int i=startPage; i<=endPage; i++){	
    if(i == currentPage){
%><li><span><%=i%></span></li>
<%  }else{
%>  
  <li><a href="<%=i%>"><span><%=i%></span></a></li>
<%  }//end if    
}//end for
if(endPage < pb.getMaxPage()){
%><li><a href="<%=endPage+1%>"><span>NEXT</span></a></li>
<%} %>
</ul>
</div>




