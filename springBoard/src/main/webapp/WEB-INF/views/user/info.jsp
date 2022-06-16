<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<% 
	String name = (String) request.getAttribute("name"); 
	int age = (Integer) request.getAttribute("age");
	String addr = (String) request.getAttribute("addr");
	String phone = (String) request.getAttribute("phone");

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- <%=name %><br>
	<%=age %><br>
	<%=addr %><br>
	<%=phone %><br> --%>
	
	<!-- 0616 회원가입 후 정보 뜨기 04. -->
	${vo.name}<br>
	${vo.age}<br>
	${vo.addr}<br>
	${vo.phone}<br>
</body>
</html>
