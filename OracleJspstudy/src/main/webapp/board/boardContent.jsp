<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- C12. 담겨진 데이터 꺼내기 -->
<%@page import = "jspstudy.domain.BoardVo" %>
<%
	BoardVo bv = (BoardVo)request.getAttribute("bv");
%>
	
<!-- C1. 글쓰기 페이지 만들기 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기 페이지</title>
<style>
h1{width:800px; margin:auto; padding: 10px 0px;}

.formBox{width:800px; margin:auto; padding: 10px 0px;}
.smallTable {width: 100%;border-collapse:collapse; border:1px solid green;}
.smallTable td{border:1px solid green;}
.smallTable tr {border:1px solid green;}
.smallTable td:nth-child(1) {text-align:center;width:100px;}
.smallTable .sub {padding:10px;font-size:18px;border:none;}
.smallTable .con {min-height: 300px;padding:10px;font-size:18px;border:none;}
.smallTable .wrt {padding:10px;font-size:18px;border:none;}

button{cursor:pointer;  background:none; border:none;}
.btnarea{width:800px; margin:auto; text-align:center; margin-top:10px;}
.btnarea .btn1 { width: calc( ( 100% / 4 ) - 7px );  float:left; color:white; background:#edb600;cursor:pointer; margin:3px 3px 3px 0px; padding: 10px 20px; font-weight: 600;font-size: 15px;border-radius: 5px; border: 1px solid #edb600;box-shadow: #c9c9c9 0px 0px 10px;}
.btnarea .btn1:hover {background:#a68700; border: 1px solid #a68700;}
.btnarea .btn2 { width: calc( ( 100% / 8 ) - 7px );  float:right; color:#5d874e; background:white;cursor:pointer; margin:3px 0px 3px 7px; padding: 10px 20px; font-weight: 600;font-size: 15px;border-radius: 5px; border: 1px solid white;box-shadow: #c9c9c9 0px 0px 10px;}
.btnarea .btn2:hover {border: 1px solid #5d874e;}
.btnarea .btn3 {width: calc( ( 100% / 8 ) - 7px );  float:right; color:white; background:#b3b3b3;cursor:pointer; margin:3px 0px 3px 7px; padding: 10px 20px; font-weight: 600;font-size: 15px;border-radius: 5px; border: 1px solid #b3b3b3;box-shadow: #c9c9c9 0px 0px 10px;}
.btnarea .btn3:hover {background:#8a2121; border: 1px solid #8a2121;}
.btnarea .btn4 {width: calc( ( 100% / 8 ) - 7px );  float:right; color:white; background:#5d874e;cursor:pointer; margin:3px 0px 3px 7px; padding: 10px 20px; font-weight: 600;font-size: 15px;border-radius: 5px; border: 1px solid #5d874e;box-shadow: #c9c9c9 0px 0px 10px;}
.btnarea .btn4:hover {background:#405c36; border: 1px solid #405c36;}
</style>
<script>

</script>
</head>
<body>
	<h1>게시판 내용보기</h1>
	<!--게시판 내용보기-->
	<div class="formBox">
		<table class="smallTable">
			<tr>
				<td><b>제목</b></td>
				<!-- C13. bv로 담겨진 데이터 꺼내기 -->
				<td><div class="sub"><%=bv.getSubject() %></div></td>
			</tr>
			<tr>
				<td><b>내용</b></td>
				<td><div class="con"><%=bv.getContent() %></div></td>
			</tr>
			<!-- 파일업로드 보기 1 : 파일 다운로드 추가-->
			<tr>
				<td><b>파일<br>다운로드</b></td>
				<%//파일업로드 보기 4  이미지가 없는경우
				if (bv.getFilename() != null) {%>
				<td><img width="100" src="<%=request.getContextPath() %>/images/<%=bv.getFilename() %>"><br>
					<!--파일 다운로드 11-->
			 		<a href="<%=request.getContextPath()%>/board/fileDownload.do?filename=<%=bv.getFilename()%>"><%=bv.getFilename() %></a>
			 	</td>
		
			 	<%}%>
			</tr>
			<tr>
				<td><b>작성자</b></td>
				<td><div class="wrt"><%=bv.getWriter() %></div></td>
			</tr>
		</table>
	</div>
	<div class="btnarea">
		<button type="button" name="list" value="목록" class="btn1"
			onclick="location.href='<%=request.getContextPath()%>/board/boardList.do'">목록</button>

		<!--C14. 넘길때 파라미터 ?방식으로 넘기는 방식이 get 방식 (post보다 빠르다)-->
		<input type="button" name="reply" value="답변" class="btn4"
			onclick="location.href='<%=request.getContextPath()%>/board/boardReply.do?bidx=<%=bv.getBidx()%>&originbidx=<%=bv.getOriginbidx()%>&depth=<%=bv.getDepth()%>&level_=<%=bv.getLevel_()%>'">
		<input type="button" name="delete" value="삭제	" class="btn3"
			onclick="location.href='<%=request.getContextPath()%>/board/boardDelete.do?bidx=<%=bv.getBidx()%>'">
		<input type="button" name="modify" value="수정" class="btn2"
			onclick="location.href='<%=request.getContextPath()%>/board/boardModify.do?bidx=<%=bv.getBidx()%>'">
	</div>

</body>
</html>