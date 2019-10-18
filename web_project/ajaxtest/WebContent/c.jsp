<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String[] allowOriginArr = 
   {"http://localhost:8080"
  , "http://192.168.30.102:8080"
  , "http://192.168.30.98:8080"
  , "http://192.168.30.47:8080"};%>    
<%String requestOrigin = request.getHeader("Origin");%>
<%if(request.getHeader("Origin")!=null){ //AJAX요청인 경우
	for(String allowOrigin:allowOriginArr){
		if(allowOrigin.equals(requestOrigin)){
			//response.setHeader("Access-Control-Allow-Origin",allowOrigin);//CORS정책상 1개만 등록가능
			response.setHeader("Access-Control-Allow-Origin", "*");
			break;
		}
	}	
}%>
{"msg":"다른도메인의 c.jsp가 보내준 결과입니다."}