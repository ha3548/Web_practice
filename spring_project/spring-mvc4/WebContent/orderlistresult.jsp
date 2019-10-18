<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="contextPath.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>orderlistresult.jsp</title>
</head>
<body>
<table>
<tr>
<th>주문번호</th><th>주문시간</th><th>상품번호</th><th>상품명</th><th>가격</th><th>주문수량</th>
</tr>
<c:forEach var="orderinfo" items="${requestScope.list}">
<c:forEach var="orderline" items="${orderinfo.orderLines}">
<c:set var="product" value="${orderline.product}"/>
<tr>
<td>${orderinfo.order_no}</td>
<td>${orderinfo.order_time}</td>
<td>${product.prod_no}</td>
<td>${product.prod_name}</td>
<td>${product.prod_price}</td>
<td>${orderline.order_quantity}</td>
</tr>
</c:forEach>
</c:forEach>
</table>
</body>
</html>