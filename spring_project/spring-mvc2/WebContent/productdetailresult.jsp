<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="contextPath.jsp" %>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>productdetailresult.jsp</title>
<script>
$(function(){
	var jsonStr = $("div span").html();
	obj = JSON.parse(jsonStr);
	
	var prod_no = obj.prod_no;
	var prod_name = obj.prod_name;
	var prod_price = obj.prod_price;
	var prod_detail = obj.prod_detail;
	
	$("#prod_no").val(prod_no);
	$("#prod_name").val(prod_name);
	$("#prod_price").val(prod_price);
	
	document.getElementById("imageid").src="images/"+prod_no+".jpg";
	
	if(prod_detail==null){
		$("div.detail").hide();
	} else {
		$("#prod_detail").text(prod_detail);
	}
	
	$("form").submit(function(){
		var quantity = $("input[name=quantity]").val();
		//console.log(quantity);
		
		$.ajax({
			url:'${contextPath}/addcart',
			method:'POST',
			data:'prod_no='+prod_no+'&prod_name='+prod_name+'&prod_price='+prod_price +'&quantity='+quantity,
			success:function(data){
				var jsonObj = JSON.parse(data);
				if(jsonObj.status == 1){
					alert("장바구니에 추가되었습니다.");
				}
			}
		});
		return false;
	});

});

</script>
</head>
<body>
<c:set var="p" value="${requestScope.result}"/>
<div class="productinfo">
<span style="display: none;">${p}</span>
	<img id="imageid"><br>
	<div class="detail">상품설명 : <span id="prod_detail"></span></div>
	
<form>
	상품번호 : <input name="prod_no" id="prod_no"><br>
	상품명 : <input name="prod_name" id="prod_name"><br>
	상품가격 : <input name="prod_price" id="prod_price"><br>
	상품수량 : <input type="number" name="quantity"><br>
	<button type="submit">장바구니넣기</button>
</form>
</div>

</body>
</html>