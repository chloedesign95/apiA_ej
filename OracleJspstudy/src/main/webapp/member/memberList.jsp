<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jspstudy.domain.*"%>
<%@ page import="java.util.*"%>


<%
//select 쿼리를 사용하기 위해서 function에서 메소드를 만든다.
//select memberSelectAll 메소드를 호출한다
/* MemberDao md = new MemberDao(); //MemberDao 객체생성 , 객체생성 동시에 conn도 생성됨
ArrayList<MemberVo> alist = md.memberSelectAll(); */
//테스트 : out.println(alist.get(0).getMembername()+"<br>");

ArrayList<MemberVo> alist = (ArrayList<MemberVo>) request.getAttribute("alist");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>
<style>
table {border-collapse:collapse; border: 1px solid black;width: 800px;}
td{padding:10px 20px 10px 20px;}
th{padding:10px 20px 10px 20px;}
tr{border:1px solid black;}

</style>
</head>
<body>
<!-- ml마지막. 로그인 이후 사용자 정보 보여주기-->
<%
if (session.getAttribute("midx")!=null){
  out.println("회원아이디:"+ session.getAttribute("memberId")+"<br>");
  out.println("회원이름:"+ session.getAttribute("memberName")+"<br>");

  // lo1. 로그아웃. 로그아웃 출력
  out.println("<a href='"+request.getContextPath()+"/member/memberLogout.do'>로그아웃 </a>");
}
%>
	<h1>회원목록</h1>

	<!-- 회원목록 테이블 -->
	<table>
		<tr>
			<th>번호</th>
			<th>회원이름</th>
			<th>이메일</th>
			<th>연락처</th>
			<th>작성일</th>
		</tr>

		<!-- 향상된 for문을 사용해서 tr 반복 -->
		<%
		for (MemberVo mv : alist) {
		%>
		<tr>
			<td><%=mv.getMidx()%></td>
			<!-- =mv.getMidx()는 out.println(mv.getMidx()); 과 같다 -->
			<td><%=mv.getMembername()%></td>
			<td><%=mv.getMemberemail()%></td>
			<td><%=mv.getMemberphone()%></td>
			<td><%=mv.getWriteday()%></td>
		</tr>
		<%
		}
		%>
	</table>
</body>
</html>