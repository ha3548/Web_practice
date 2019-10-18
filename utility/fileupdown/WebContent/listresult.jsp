<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
$(function(){
	$("a").click(function(){
		var url = "./down";
		var data = "fileName="+$(this).html().trim();
/*		$.ajax({
			url: url,
			data: data,
			success: function(data){
				alert("download OK");
			}
		});
		return false;
*/
	});
	
});
</script>
<meta charset="UTF-8">
<title>listresult.jsp</title>
</head>
<body>
<c:forEach var="i" items="${requestScope.list}">
	<a href="files/${i}">${i}</a><br>
</c:forEach>
</body>
</html>