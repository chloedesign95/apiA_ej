<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<% String data = (String)request.getAttribute("serverTime"); %>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<%= data %>

<h1>안녕</h1>
<a href="sample1.do">sample1.do</a> |
<a href="sample2.do">sample2.do</a> |
<br>
<a href="user/register.do">register로 이동</a>
	 <!-- 0616  회원가입하기 01.-->
	 <a href="user/join.do">회원가입하기</a>	
</body>
</html>










