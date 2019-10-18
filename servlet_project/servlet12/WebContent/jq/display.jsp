<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
div{
background-color: #F7F7F7;
width:80%;
border:1px solid;
padding:20px;
}
div.panel{
height:250px;
}
div.result{
width:70%;
height:100px;
}
div>select{
cursor: pointer;
margin: 10px;
}
button{
 color: #fff;
 background-color: #1553A3;
 border: 1px solid transparent;
 border-radius: 4px;
}
.icon-search{
display: inline-block;
width: 16px;
height: 16px;
background-image: url(../images/icon-search.png);
background-size: 16px;
background-repeat: no-repeat;
vertical-align: middle;
margin-top: -1px;
}
.mr-3 {
margin-right: 3px !important;
}
input[type=checkbox] ~ div{
display:none;
}
</style>
<script>
$(function(){
	//dom에서 id속성값이 sido인 객체 찾기
	var $sidoObj = $("#sido");
	var $gunguObj = $("#gungu");
	
	$sidoObj.change(function(){
		//console.log($sidoObj.val());
		var options = '<option>시/군/구 선택</option>';
		$gunguObj.empty();
		$gunguObj.append(options);
				
		switch($sidoObj.val()){
		
		case 's':
			$gunguObj.append('<option>강남시</option>');
			$gunguObj.append('<option>강북시</option>');
			$gunguObj.append('<option>강서시</option>');
			$gunguObj.append('<option>강동시</option>');
			break;
			
		case 'k':
			$gunguObj.append('<option>수원시</option>');
			$gunguObj.append('<option>시흥시</option>');
			$gunguObj.append('<option>안산시</option>');
			break;
		}
		
	});
	
	//button객체찾기
	var $btObj = $("button");
	$btObj.click(function(){
		var $divResultObj = $(".result");
		if($sidoObj.val() == 'k' && $("#gungu").val() == '시흥시'){
			$divResultObj.html('그린웨이 자전거길');
		}
	});
	
	var $ckAllObj; //전체 checkbox
	var $ckArr = $("input[type=checkbox]");
	//1.'전체'checkbox찾기
	//$.each($ckArr, function(i, ckObj){
	$ckArr.each($ckArr, function(i, ckObj){
		console.log("$.each() i=" + i); //0, 1, 2
		var $sibling = $(ckObj).next();
		if($sibling.html().trim()=='전체'){
			$ckAllObj = $(ckObj);
			//console.log($sibling.html() +":" + $ckAllObj);
			//break;
			return false; //callback함수 내부에서는 return을 써준다.
		}
	});
	
	/* for(var i=0; i<$ckArr.length; i++){
		var ckObj = $ckArr[i];
		
		var $sibling = $(ckObj).next();
		if($sibling.html().trim()=='전체'){
			$ckAllObj = $(ckObj);
			console.log($sibling.html() +":" + $ckAllObj);
			break;
		} 
	} */

	
	//2.'전체'checkbox가 선택
	//나머지 checkbox checked속성값 = 전체 속성값
	
	$ckAllObj.click(function(){
		console.log("all clicked!" + $ckAllObj.prop('checked'));
		for(var i=0; i<$ckArr.length; i++){
			if($($ckArr[i]) != $ckAllObj){
				$($ckArr[i]).prop('checked',$ckAllObj.prop('checked'));
				//$ckArr[i].click(); //클릭이벤트 강제발생시킨다
			}
		}
	});
	
	//3. 세부조건 표시
	for(var i=0; i<$ckArr.length; i++){
		var ckObj = $ckArr[i];
		if($(ckObj) != $ckAllObj){			
		  $(ckObj).change(function(){
			 console.log($(ckObj).next().next());
			var $sibling = $(ckObj).next().next();
			if($(ckObj).prop('checked')){
				$sibling.show();
			}else{
				$sibling.hide();
			}
		  });
		}	
	} 
});

</script>
</head>
<body>
<div class="panel">
  <div>
       지역 : 
      <select id="sido">
        <option value="0">도/시 선택</option>
        <option value="s">서울</option>
        <option value="k">경기</option>
      </select>
      <select id="gungu">
        <option>시/군/구 선택</option>  
      </select>
  </div>
  <div> 
      조건:
    <input type="checkbox"><span>  전체   </span> 
    <hr>
    <input type="checkbox"><span>거리</span>
    <div>
      <input type="radio">5km미만&nbsp;&nbsp;      
      <input type="radio">5~10km&nbsp;&nbsp;
      <input type="radio">10km초과
    </div>
     
    <input type="checkbox"><span>난이도</span><br>
    
    <input type="checkbox"><span>시간</span><br>    
  </div>

<button>
<i class="icon-search mr-3"  style="margin-right:10px;"></i>
검색하기</button>
</div>
<div class="result">

</div>
</body>
</html>