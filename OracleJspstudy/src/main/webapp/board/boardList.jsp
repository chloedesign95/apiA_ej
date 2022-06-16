<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--L13.  import하기 -->
<%@page import ="java.util.ArrayList" %>
<%@page import ="jspstudy.domain.*" %>

<% //L12
ArrayList<BoardVo> alist  = (ArrayList<BoardVo>)request.getAttribute("alist");
//pg.36. 페이지 메이커 연결해주기
PageMaker pm = (PageMaker)request.getAttribute("pm");
%>

<!-- L3. boardList.jsp 만들기 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>
<style>
table {border-collapse:collapse; border: 1px solid black;width: 1000px;}
td{padding:10px 20px 10px 20px;}
th{padding:10px 20px 10px 20px;}
tr{border:1px solid black;}

</style>
</head>
<body>
	<h1>글 목록</h1>
	
	<!-- 검색 6 : form 만들기 -->
	<form name="frm" action="<%=request.getContextPath() %>/board/boardList.do" method="post">
	
		<!--검색 1 : 검색기능 table 공간 만들기 -->
		<table style="border:none; width:1000px; text-align:right;">
			<tr style="border:none;">
				<td style="width:800px;">
					<select name="searchType">
						<option value="subject">제목</option>
					    <option value="writer">작성자</option>
					</select>
				</td>
				<td>
					<input type="text" name="keyword" size="10">
				</td>
				<td>
					<input type="submit" name="submit" value="검색">
				</td>
			</tr>
		</table>
    <!-- 검색 6 : form 만들기 -->
	</form>

	<!-- 회원목록 테이블 -->
	<table>
		<tr>
			<th>bidx번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
		
		<!-- L17. 반복문을 사용해서 tr을 반복 -->
		<% for (BoardVo bv : alist) {%>
		<tr>
			<td><%=bv.getBidx() %></td>
			<td>
				<%
				for(int i=1; i<=bv.getLevel_();i++){
				  out.print("&nbsp;&nbsp;");
				  if (i== bv.getLevel_()){
				    out.println("ㄴ");
				  }
				}
				%>
				
				<!-- C2. 제목을 누르면 게시글 보는 페이지로 가는 가상경로--><!-- bidx값 넘기기 -->
				<a href="<%=request.getContextPath()%>/board/boardContent.do?bidx=<%=bv.getBidx()%>"><%=bv.getSubject()%></a>
				
				<!-- L14. tr을 반복 -->
			</td>
			<td><%=bv.getWriter() %></td>
			<td><%=bv.getWriteday() %></td>
		</tr>
		<% } %>
		
	</table>
	
	<!-- pg1. 페이징 paging 처리 만들기 -->
	<table style="border:none; width:800px; text-align:center; ">
	<%
		if(pm.getTotalCount() ==0){
		  out.print("<tr><td colspan='4'>검색된 데이터 없음. </td></tr>");
		}
	%>
	<tr style="border:none;">
		<td style="width:200px;text-align:right;">
		<!-- pg38. -->
			<%-- <% 
				if (pm.isPrev() == true){
					out.println("<a href='"+request.getContextPath()+"/board/boardList.do?page="+(pm.getStartPage()-1)+"'>◀</a>");
				}
			%> --%>  
		  		<!--검색 링크 1. -->
		  		<% if (pm.isPrev() == true){
							out.println("<a href='"+request.getContextPath()+"/board/boardList.do?page="+(pm.getStartPage()-1)+"&keyword="+pm.encoding(pm.getScri().getKeyword())+"&searchType="+pm.getScri().getSearchType()+"'>◀</a>");
						}
				%>  
		</td>
		<td>
			 <!-- pg37. for문으로 꺼내기 -->
				<%-- <%
					for (int i=pm.getStartPage(); i<= pm.getEndPage();i++){
						out.println("<a href='"+request.getContextPath()+"/board/boardList.do?page="+i+"'>"+i+"</a>");	
					}
				%> --%>
			 <!--검색 링크 3 . -->  <!--검색 추가 기능 5 : -->
			 	<%
					for (int i=pm.getStartPage(); i<= pm.getEndPage();i++){
						  if(i == pm.getScri().getPage()){  // 해당 페이지의 페이징 번호 style 강조
								out.println("<a href='"+request.getContextPath()+"/board/boardList.do?page="+i+"&keyword="+pm.encoding(pm.getScri().getKeyword())+"&searchType="+pm.getScri().getSearchType()+"' style='font-weight:bold; color:#ff0080; font-size:1.2em'>"+i+"</a>");	
						  }
						  else{
								  out.println("<a href='"+request.getContextPath()+"/board/boardList.do?page="+i+"&keyword="+pm.encoding(pm.getScri().getKeyword())+"&searchType="+pm.getScri().getSearchType()+"'>"+i+"</a>");	
				    		}
					 }
				%>
		</td>
	 	<td style="width:200px;text-align:left;">
	 	<!-- pg38. -->
				<%-- <%
					if(pm.isNext() && pm.getEndPage() >0) {
						out.println("<a href='"+request.getContextPath()+"/board/boardList.do?page="+(pm.getEndPage()+1)+"'>▶</a>");	
					}  
				%>  --%>
			  <!--검색 링크 2. -->
			  <% if(pm.isNext() && pm.getEndPage() >0) {
						out.println("<a href='"+request.getContextPath()+"/board/boardList.do?page="+(pm.getEndPage()+1)+"&keyword="+pm.encoding(pm.getScri().getKeyword())+"&searchType="+pm.getScri().getSearchType()+"'>▶</a>");	
					}  
				%> 
		</td>
	</tr>
	</table>
	
</body>
</html>