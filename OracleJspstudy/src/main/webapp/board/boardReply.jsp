<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Reply6. 연결 -->
<%@page import ="jspstudy.domain.BoardVo"%>
<%BoardVo bv = (BoardVo)request.getAttribute("bv");%>

<!-- Reply1. 답변하기페이지 만들기 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 답변하기</title>
<style>
body{font-family: Arial, Helvetica, sans-serif;}
h1{width:800px; margin:auto; padding: 10px 0px;}

.formBox{width:800px; margin:auto; padding: 10px 0px;}
.smallTable {width: 100%;border-collapse:collapse;}
.smallTable tr {border: 1px solid blue;}
.smallTable td {border: 1px solid green;}
.smallTable td:nth-child(1) {text-align:center;width:100px;}
.smallTable input[type=text] {font-family:auto; width: -webkit-fill-available;padding:10px;font-size:18px;border:1px solid #bfbfbf;}
.smallTable input:focus {outline:none;}
.smallTable textarea {font-family:auto; width: -webkit-fill-available;min-height: 300px;padding:10px;font-size:18px;border:1px solid #bfbfbf;}
.smallTable textarea:focus {outline:none;}
.readonly{border:none !important;}

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

//게시하기 버튼 체크함수 만들기
function check() {
  //alert("test"); //메소드 실행하여 check라는 메소드가 실행되는지테스트

  var fm = document.frm; //frm 객체를 fm으로 담겠다

  if (fm.subject.value == "") { //input이 비어있을때 알림창을 띄운다
      alert("제목을 입력하세요");
      fm.subject.focus();
      return;
  }else if (fm.content.value==""){
  	alert("내용을 입력하세요");
  	fm.content.focus();
  	return;
  }else if (fm.writer.value==""){
  	alert("작성자를 입력하세요");
  	fm.writer.focus();
  	return;
  }
  
  //가상경로를 사용해서 페이지 이동시킨다
  fm.action = "<%=request.getContextPath()%>/board/boardReplyAction.do";
  fm.method ="post"; //값을 감추어서 넘기는 방식
  fm.submit(); //넘긴다

  return;
  	
}
</script>
</head>
<body>
	<h1>답변하기</h1>
	<!-- 글쓰기 table 시작 -->
	<div class="formBox">
		<form name="frm">
		<!-- Reply7. 연결 -->
		<input type ="hidden" name="bidx" value="<%=bv.getBidx()%>">
		<input type ="hidden" name="originbidx" value="<%=bv.getOriginbidx()%>">
		<input type ="hidden" name="depth" value="<%=bv.getDepth()%>">
		<input type ="hidden" name="level_" value="<%=bv.getLevel_()%>">
		
			<table class="smallTable">
				<tr>
					<td><b>제목</b></td>
					
					<td><input type="text" name="subject" placeholder="제목을 입력해주세요"></td>
				</tr>
				<tr>
					<td><b>내용</b></td>
					<td><textarea name="content" placeholder="내용을 입력해주세요"></textarea></td>
				</tr>
				<tr>
					<td><b>작성자</b></td>
					<td><input class="readonly" type="text" name="writer"value="<%=session.getAttribute("memberName") %>" readonly></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="btnarea">
	
	
		<button type="button" name="list" value="목록" class="btn1"
			onclick="location.href='<%=request.getContextPath()%>/board/boardList.do'">목록</button>
        
        <input type="button" name="delete" value="삭제	" class="btn3" onclick="">
		<input type="button" name="btn" value="게시" class="btn2" onclick="check();">
	</div>

</body>
</html>