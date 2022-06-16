<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>session 정보</h2> 
	${user.name } ////
	${user.age }
	<form method="post" action="register.do">
		이름: <input type="text" name="name"><br>
		나이: <input type="number" name="age"><br>
		주소: <input type="text" name="addr"><br>
		연락처: <input type="text" name="phone"><br>
		<input type="submit" value="전송">
	</form>
</body>
</html>