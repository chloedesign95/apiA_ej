<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>바꿔서다시올림</h1>
	<h1>되게 해줘어어어</h1>
	<a  href="<%=request.getContextPath() %>/member/memberJoin.do">회원가입하기</a>
	<a href="<%=request.getContextPath() %>/member/memberList.do">
		<!-- 상대경로 : 자기 위치에서 memberList.jsp로 이동한다 -->회원 목록가기
	</a>
	<a href="<%=request.getContextPath() %>/member/memberLogin.do">로그인</a>
	<a href="<%=request.getContextPath() %>/board/boardWrite.do">게시판 글쓰기</a>
	<a href="<%=request.getContextPath() %>/board/boardList.do">게시판 리스트</a>  <!-- L. -->
</body>
</html>