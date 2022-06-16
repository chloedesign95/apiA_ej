<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <!-- 0616  회원가입하기 02. 회원가입.jsp 생성 및 html 작성-->
 
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원가입하기</title>
</head>
<body>
	<h2>회원가입 화면</h2>
	<form action="join.do" method="post">
		id: <input type="text" name="id"><br>
		password: <input type="password" name="password"><br>
		name: <input type="text" name="name"><br>
		<input type="submit" value="회원가입">
	</form>
</body>
</html>