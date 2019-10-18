<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="contextPath.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cartlistresult.jsp</title>
<script>
$(function(){
	$("form").submit(function(){
		var prod_noArray = [];//주문할(체크된) 상품번호 담을 배열
        $("input[type=checkbox]:checked").each(function(){
           prod_noArray.push($(this).parent().next().html());
        });

		$.ajax({
		       url: '${contextPath}/addorder',
		       method: 'POST',
		       data: 'prod_noArray='+prod_noArray,
		       success: function(data) {
		          var jsonObj = JSON.parse(data); 
		          	if(jsonObj.status == 0){
						alert("로그인 후 주문가능합니다.");
					} else {
						alert("주문이 완료되었습니다.");
					}
		       }
		});
		return false;
	})
});
</script>
</head>
<body>
<c:set var="cart" value="${sessionScope.cart}"/>
<form>
	<table>
	<tr><th></th><th>상품번호</th><th>상품명</th><th>가격</th><th>주문수량</th></tr>
	  <c:forEach var="c" items="${cart}">
		<c:set var="p" value="${c.key}"/>
		<tr class="prod_data">
		<td><input type="checkbox" value="${p.prod_no}" id="${p.prod_no}"></td>
		<td>${p.prod_no}</td>
		<td>${p.prod_name}</td>
		<td>${p.prod_price}</td>
		<td>${m.value}</td>
		</tr>
	  </c:forEach>
	</table>
  	<button>주문하기</button>
</form>
</body>
</html>