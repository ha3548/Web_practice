<%@page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta charset="UTF-8">
<style>
.mb-15 {
    margin-bottom: 15px !important;
}
.clearfix:before, .clearfix:after {
    content: " ";
    display: table;
}
h6 {
    font-size: 18px;
    letter-spacing: -1px;
    line-height: 24px;
}

.mb-0 {
    margin-bottom: 0px !important;
}

.mt-0 {
    margin-top: 0px !important;
}
.pull-left {
    float: left !important;
}
.pull-right {
    float: right !important;
}
.text-primary {
    color: #2BC63E;
    font-weight: bold;
}

.form-group-with-btn {
    padding-right: 100px;
    position: relative;
}
.form-group-with-btn2 {
    padding-right: 200px;
    position: relative;
}
.form-group-with-btn>.btn{
   position: absolute;
    top: 8px;
    right: 0;
}
.form-group-with-btn2>.btn{
    position: absolute;
    top: 8px;
    right: 0;
display: inline-block;
}
.form-control {
    /* display: block; */
    width: 100%;
    height: 44px;
    padding: 6px 12px;
    font-size: 14px;
    line-height: 1.428571429;
    color: #000;
    letter-spacing: -0.5px;
    background-color: #fff;
    background-image: none;
    border: 1px solid #ccc;
    border-radius: 2px;
}
button, input, optgroup, select, textarea {
    color: inherit;
    font: inherit;
    margin: 0;
    margin-top: 0px;
    margin-right: 0px;
    margin-bottom: 0px;
    margin-left: 0px;
}
.btn-primary.btn-line {
    color: #2BC63E;
    background-color: #fff;
    border-color: #2bc63e;
}

.btn-primary.btn-line:active:hover, .btn-primary.btn-line:active:focus, .btn-primary.btn-line:active.focus, .btn-primary.btn-line.active:hover, .btn-primary.btn-line.active:focus, .btn-primary.btn-line.active.focus, .open>.btn-primary.btn-line.dropdown-toggle:hover, .open>.btn-primary.btn-line.dropdown-toggle:focus, .open>.btn-primary.btn-line.dropdown-toggle.focus {
    color: #229c31;
    background-color: #fff;
    border-color: #229c31;
}
.btn-primary.btn-line:active, .btn-primary.btn-line.active, .open>.btn-primary.btn-line.dropdown-toggle {
    background-image: none;
}
.btn {
    display: inline-block;
    margin-bottom: 0;
    font-weight: normal;
    text-align: center;
    vertical-align: middle;
    touch-action: manipulation;
    cursor: pointer;
    background-image: none;
    border: 1px solid transparent;
    min-width: 180px;
    height: 44px;
    letter-spacing: -1px;
    font-weight: 700;
    white-space: nowrap;
    padding: 0px 18px;
    font-size: 14px;
    line-height: 42px;
    border-radius: 4px;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
}
   
.bold {
    font-weight: 700 !important;
}
.btn.btn-small {
    min-width: 100px;
}

input[type=submit], #divSearchZip {
  display: none;
}

/*  우편번호 창  */
#divSearchZip{
    padding : 8px;
    width:300px; height:300px; 
    position: absolute; 
    top:100px; 
    left:150px;
    border: 1px solid transparent;
    background-color: 	#E6E6FA;  
}
#divSearchZip .search_pop input[type=text] {
    width: 80%;
    height: 36px;
    font-size: 14px;
    line-height: 16px;
    padding: 8px;
    margin: 0px;
    border: 2px solid #ee2e24;
    box-sizing: border-box;
    float: left;
}
#divSearchZip .search_pop button {
    width: 20%;
    height: 36px;
    background-color: #ee2e24;
    font-size: 14px;
    color: #fff;
    font-weight: bold;
    text-align: center;
    line-height: 32px;
    display: block;
    padding: 0px;
    margin: 0px;
    border: 0px;
    border: 2px solid #ee2e24;
    box-sizing: border-box;
    float: left;
}
#divSearchZip table {
    width: 100%;
    padding: 0px;
    margin: 10px 0px 20px 0px;
    border-bottom: 2px solid #888;
    color: #707070; 
}
#divSearchZip tr:not(:first-child):hover{
    cursor: pointer;
    
    font-weight: bold;
}
th, td {
    vertical-align: middle;
} 
#divSearchZip > div{
    clear:both;
    overflow: auto;
    height:80%;
}

</style>
<!-- <script src=
           "https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 -->
<script>
$(function(){
	//id입력란에 focus이벤트감시, 할 일: 가입완료버튼 화면에서 사라진다
	var $idObj = $("input[name=id]");
	var $submitObj = $("input[type=submit]");
	$idObj.focus(function(){
		$submitObj.hide();
	});	
	//중복확인버튼 click이벤트감시
	var $dupchkObj =  $("#dupchk");
	$dupchkObj.click(function(){
		if($idObj.val().trim().length == 0){
			$idObj.focus();
			return false;
		}
		$.ajax({
			url: '${contextPath}/dupchk',
			method: 'POST',
			data : 'id='+$idObj.val(),
			success: function(data){
				var jsonObj = JSON.parse(data);
				if(jsonObj.status == 1){
					alert("이미 존재하는 아이디입니다");
				}else {
					$submitObj.show();
				}
			}
		});
		return false;
	});	 
	//우편번호 검색버튼
	var $searchZipObj = $("#searchZip");
	$searchZipObj.click(function(){
		$("#divSearchZip form input[name=doro]").val('');
		$("#divSearchZip>div>table").empty();
		$("#divSearchZip").show();
	});	
	
	//#divSearchZip의 하위요소로 form객체찾기
	$("#divSearchZip form").submit(function(){
		//../searchzip ajax요청
		//응답결과를 "#divSearchZip>div>table"객체의 innerHTML로 추가
		var doroObj = $("#divSearchZip form input[name=doro]");
		if (doroObj.val().trim() == ''){
			doroObj.focus();
			return false;
		}
		$.ajax({
			url: '${contextPath}/searchzip',
			method: 'POST',
			data: 'doro='+ doroObj.val(),
			success://function(jsonObjArr){ 
				function(data){
				//alert(jsonObjArr);
				var jsonObjArr = JSON.parse(data);
				var trs = "";
			    trs += "<tr><th>우편번호</th><th>주소</th></tr>";				
				for(var i=0; i<jsonObjArr.length; i++){
					var jsonObj = jsonObjArr[i];//객체{}
				    trs += "<tr id="+jsonObj.buildingno+">"+
				               "<td>"+jsonObj.zipcode+"</td>"+
				               "<td><div>"+jsonObj.addrdoro+"</div>"+
				                   "<div>" + jsonObj.addrzibun+"</div>"+
				                  
				               "</td></tr>";				    
				}
				$("#divSearchZip>div>table").html(trs);
			}
		});
		return false;
	});
	
	//우편번호창에서 행(tr) 클릭이벤트 감시 할일: 본창의 우편번호, 기본주소에 값 대입,
	                                   //우편번호창 사라진다.
	
    $("#divSearchZip>div>table").on("click", 
    		                        "tr:not(:first-child)",
    		                        function(){
	  var children = $(this).children();
	  var zipcode =children.eq(0).html();//<td></td>	  
	  $("input[name=zipcode]").val(zipcode);
	  
	  var addrdoro = children.find("div:first-child"); //<td><div></div><div></div></td>

	  var addrdoro = $(addrdoro).html(); //<div>
	  $("input[name=addr1]").val(addrdoro);//주소1
	  $("input[name=buildingno]").val($(this).attr("id")); //건물관리번호
	  $("#divSearchZip").hide();
	  return false;
	});
	
    $("#formJoin").submit(function(){
        var url = '${contextPath}/join';
        $.ajax({
        	url: url,
        	method: 'post',
        	data: $(this).serialize(),
        	success:function(data){
        		if( $("input[name=pwd]").val() != $("input[name=pwd2]").val()){
        			alert("비밀번호가 일치하지 않습니다");
        			return false;
        		}
        		var jsonObj = JSON.parse(data);
        		if(jsonObj.status == 1){ //가입성공
        			alert("가입성공");
        		    location.href='${contextPath}'; //'${contextPath}/jq/layout.jsp';
        		}else{
        			alert("가입실패");
        		}
        	}
        });
    	return false;
    });
});
</script>	
</head>

<body>
<form id="formJoin">
<div style="height:100%; width:50%;border:1px solid;">  
  <div class="clearfix mb-15">
	<h6 class="mt-0 mb-0 pull-left">아이디 입력</h6>
	<div class="pull-right input_count" data-target="input_id" data-max="15">
		4자 이상의 영문 소문자 기준 <strong class="text-primary count">0</strong> / 15자
	</div>	
  </div>
  <div class="form-group form-group-with-btn mb-30">
    <input type="text"  name="id" class="form-control" maxlength="15" placeholder="아이디를 입력하세요.">
    <button type="button" id="dupchk"  class="btn btn-primary btn-line btn-small bold">중복확인</button>
  </div>
   <div class="clearfix mb-15">
		<h6 class="mt-0 mb-0 pull-left">비밀번호</h6>
		<div class="pull-right">
			영문소문자, 숫자 포함 <strong class="text-primary">6</strong>자리 – <strong class="text-primary">10</strong>자리
		</div>
	</div>
  <div class="form-group mb-30">
	<input type="password" name="pwd" class="form-control" placeholder="사용할 비밀번호를 입력하세요." maxlength="10">	
  </div>
							
  <div class="clearfix mb-15">
	<h6 class="mt-0 mb-0 pull-left">비밀번호 확인</h6>
  </div>
  <div class="form-group mb-30">
	<input type="password" name="pwd2" class="form-control" placeholder="비밀번호를  다시한번입력하세요." maxlength="10">
  </div>
  <div class="clearfix mb-15">
	<h6 class="mt-0 mb-0 pull-left">이름</h6>
  </div>
  <div class="form-group mb-30">
	<input type="text" name="name" class="form-control" placeholder="이름을 입력하세요." >
  </div>
  <div class="clearfix mb-15">
	<h6 class="mt-0 mb-0 pull-left">우편번호</h6>
  </div>
 
  <div class="form-group form-group-with-btn2 mb-30">
    <input type="text"  
           class="form-control"             
           readonly 
           name="zipcode"> 
    <input type="hidden" name="buildingno">
<button type="button" id="searchZip"  class="btn btn-primary btn-line btn-small bold">우편번호</button>            
  </div>
  <div class="form-group mb-30">
	<input type="text" readonly   name="addr1" class="form-control" >	
  </div>
  <div class="form-group mb-30" >
    <input type="text" name="addr2" class="form-control">
  </div>
  
  
  
  <div class="form-group mb-30" style="margin-top: 10px;margin-bottom: 10px;">
	<input type="reset"   value="취소">
	<input type="submit"  value="가입완료" >
  </div>
  
</div>					
</form>

<!-- 우편번호 검색용 div -->
<div id="divSearchZip" >
 <form>
   <div class="search_pop">
     <input type="text" placeholder="도로명 + 건물번호" name="doro">
     <button>검색</button>
   </div>
 </form>  
 <div>
   <table><!-- 크롬브라우저는 tbody태그를 끼워넣음 --> 
       <!-- <tr><th>우편번호</th><th>주소</th></tr>
       <tr><td>30114</td><td>세종특별자치시 도움5로 19 (어진동, 우정사업본부)</td></tr>
       <tr><td>30115</td><td>테스트</td></tr>     
       <tr><td>30115</td><td>테스트</td></tr>           
       <tr><td>30115</td><td>테스트</td></tr>           
       <tr><td>30115</td><td>테스트</td></tr>           
       <tr><td>30115</td><td>테스트</td></tr>           
       <tr><td>30115</td><td>테스트</td></tr>           
       <tr><td>30115</td><td>테스트</td></tr>           
       <tr><td>30115</td><td>테스트</td></tr>           
       <tr><td>30115</td><td>테스트</td></tr>           
       <tr><td>30115</td><td>테스트</td></tr>   -->
   </table>
 </div>   
</div>
</body>
</html>