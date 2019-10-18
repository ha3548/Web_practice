<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>
#dupchkid{
color: #2BC63E;
background-color: #fff;
border-color: #2bc63e;
border-radius: 4px;
}
#divSearchZip{
display: none;
}
#divSearchZip>div{
overflow: auto;
height: 80%;
}
</style>
<script>
$(function(){ //id입력란에 focus이벤트감시, 할일: 가입완료버튼화면에서 사라진다
	
	//아이디입력란
	var $idObj = $("input[name=id]");
	var $submitObj = $("input[type=submit]");
	$idObj.focus(function(){
		$submitObj.css('display','none');
	});
		
	//'중복확인' 버튼
	var $dupchkObj = $("#dupchkid");
	$dupchkObj.click(function(){
		//alert("중복확인버튼 클릭이벤트 발생");
		if($idObj.val()=="") {
			 alert("ID를 입력하세요");
			 $idObj.focus();
			 return false;
		 }
		
		$.ajax({
			url: '../dupchk',
			method: 'post',
			data: 'id=' + $idObj.val(), //'id =' 공백주의!!! -> null로 넘어가게된다ㅠㅠ
			success: function(data){			
				var jsonObj = JSON.parse(data);
				var msg = jsonObj.id + "는 ";

				if(jsonObj.status == 1){//아이디중복
					msg+="이미 존재하는 아이디입니다.";
					alert(msg);
				
				} else if(jsonObj.status == -1){//사용가능
					msg+="사용가능한 아이디입니다.";
					alert(msg);
					$submitObj.css("display","inline-block");
				}
			}
		});
		return false;
	});
	
	//'우편번호검색'버튼
	var $searchZipObj = $("#searchZip");
	$searchZipObj.click(function(){
		$("#divSearchZip form input[name=doro]").val('');
		$("#divSearchZip>div>table").empty();
		$("#divSearchZip").show();
	});	
	
	//#divSearchZip의 하위요소로 form객체찾기
	$("#divSearchZip form").submit(function(){
		var doroObj = $("#divSearchZip form input[name=doro]");
		if (doroObj.val().trim() == ''){
			doroObj.focus();
			return false;
		}
		$.ajax({
			url: '../searchzip',
			method: 'POST',
			data: 'doro='+ doroObj.val(),
			success:function(jsonObjArr){ 
				jsonObjArr = JSON.parse(jsonObjArr);
				var trs = "";
			    trs += "<tr><th>우편번호</th><th>주소</th></tr>"; //이게뭐지?????????		
				console.log("길이"+jsonObjArr.length);
			    		for(var i=0; i<jsonObjArr.length; i++){
					var jsonObj = jsonObjArr[i];//객체{}
				    trs += "<tr id="+jsonObj.buildingno+">"+"<td>"+jsonObj.zipcode+"</td>"+"<td><div>"+jsonObj.addrdoro+"</div>"+
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
	$("#divSearchZip>div>table").on("click", "tr:not(:first-child)", function(){
		var children = $(this).children();
		var zipcode =children.first().html();//<td></td>	  
		$("input[name=zipcode]").val(zipcode);
		
		var addrdoro = children.find("div:first-child"); //<td><div></div><div></div></td>
		
		var addrdoro = $(addrdoro).html(); //<div>
		$("input[name=addr1]").val(addrdoro);//주소1
		$("input[name=buildingno]").val($(this).attr("id")); //건물관리번호
		
		$("#divSearchZip").hide();
		return false;
	});
	
	//가입완료
	$("#formJoin").submit(function(){
		if($("input[name=pwd]").val() != $("input[name=pwd2]").val()) {
			alert("비밀번호가 일치하지 않습니다.");
			return false;
		}
		
		$.ajax({
			url: '../join',
			method: 'POST',
			data: $(this).serialize(),
			success:function(data){ 
				var jsonObj = JSON.parse(data);
				if(jsonObj.status == 1){
					alert("가입성공");
					location.href="layout.jsp";
				
				} else if(jsonObj.status == -1){
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
	<h3>아이디입력</h3>
	<input type="text" name="id" placeholder="아이디를 입력하세요.">
	<button id="dupchkid">중복확인</button><br>
	
	<h3>비밀번호</h3>
	<input type="password" name="pwd" placeholder="비밀번호를 입력하세요."><span></span><br>
	
	<h3>비밀번호확인</h3>
	<input type="password" name="pwd2" placeholder="비밀번호를 입력하세요."><span></span><br>
	
	<h3>이름</h3>
	<input type="text" name="name"><br>
	
	<h3>주소</h3>
	<input style="width: 80px" name="zipcode">
	<button type="button" id="searchZip">우편번호검색</button><br>
	<input style="width: 300px" name="addr1"><br>
	<input style="width: 200px" name="addr2"><br><br>
	<input type="hidden" name="buildingno" value=""><!-- 건물번호 -->
	<input type="reset" value="취소">
	<input type="submit" value="가입완료">
</form>

<!-- 우편번호 검색용 -->
<div id="divSearchZip" style="width:450px; height: 400px; position: absolute;
							  top:100px; left: 200px; background-color: lightgray">
	<form>
		<input type="text" name="doro" placeholder="도로명 + 건물번호">
		<button>검색</button>
	</form>
	
	<div>
		<table><!-- 크롬은 tbody태그를 자동끼워넣기를 하므로 tr객체찾기X tb후손으로 찾기O -->
			<tr><th>우편번호</th><th>주소</th></tr>
			<tr><td>30114</td><td>세종특별자치시 도움5로 19 (어진동, 우정사업본부)</td></tr>
			<tr><td>14248</td><td>경기도 광명시 안현로 34 (하안동, 하안3단지고층주공아파트)</td></tr>			
		</table>
	</div>

</div>
</body>
</html>