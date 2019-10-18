<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="contextPath.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>productlist.jsp</title>
<style>
img {
width: 250px;
height: auto;
}
span {
display: none;
}
</style>
<script>
$(function(){
	/*게시물 자세히보기*/
	var $tdArr = $("tr.name");
	$tdArr.click(function(){
		var $prod_no = $(this).children().eq(1).html();
		//console.log($prod_no);
		
		$.ajax({
			url:'${contextPath}/productdetail',
			data:'prod_no='+$prod_no,
			success:function(data){
				$("section").empty();
				$("section").html(data);
			}
		});
		
		return false;
	});
});
</script>
</head>
<body>
<div class="product">
<table>
	<c:forEach var="p" items="${list}" varStatus="status">
		<tr class="img">
			<td><img src="images/${p.prod_no}.jpg"></td>
		</tr>
		<tr class="name">
			<td>${p.prod_name}</td>
			<td style="display: none;">${p.prod_no}</td>
		</tr>
	</c:forEach>
</table>
</div>
</body>
</html>