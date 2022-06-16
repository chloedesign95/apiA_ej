<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String data = (String)request.getAttribute("key");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>sample02.jsp 입니다.</h2>
	<%= data %>
	<!-- 
		home.jsp에서 sample2.do 링크 클릭시
		sample02.jsp로 포워딩 하세요.
		이때 컨트롤러에서는 'hello'라는 데이터
		를 받아와서 출력하세요.
	 -->
</body>
</html>