<%@page import="com.kh.mvc.board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>

<%
	// 업데이트 코드부!!
	Board board = (Board)request.getAttribute("board");
%>

<style>
	div#board-write-container
	{
		width:600px;
		margin:0 auto;
		text-align:center;
	}
	
	div#board-write-container h2
	{
		margin:10px 0;
	}
	
	table#tbl-board
	{
		width:500px;
		margin:0 auto;
		border:1px solid black;
		border-collapse:collapse;
	}
	
	table#tbl-board th
	{
		width:125px;
		border:1px solid;
		padding:5px 0;
		text-align:center;
	}
	
	table#tbl-board td
	{
		border:1px solid;
		padding:5px 0 5px 10px;
		text-align:left;
	}
</style>


<section id="content">
	<div id='board-write-container'>
		<h2>게시판 작성</h2>
		<form action="<%= request.getContextPath()%>/board/update" method="POST" enctype="multipart/form-data">
			<input type="hidden" name="boardNo" value="<%=board.getNo()%>">
			<input type="hidden" name="originalFileName" value="<%=board.getOriginalFileName()%>">
			<input type="hidden" name="renameFileName" value="<%=board.getRenamedFileName()%>">

			<table id='tbl-board'>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" id="title" value="<%=board.getTitle()%>"></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" name="writer" value="<%= board.getWriterId() %>" readonly></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td>
						<input type="file" name="reloadFile"><br>
						<% if(board.getOriginalFileName() != null) { %>
							<img src="<%= request.getContextPath() %>/resources/images/file.png" width="20" height="20"/>
							<%= board.getOriginalFileName() %>
						<% } %>
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea name="content" cols="50" rows="15"><%=board.getContent()%></textarea></td>
				</tr>
				<tr>
					<th colspan="2">
						<input type="submit" value="수정">
						<input type="button" onclick="location.replace('<%= request.getContextPath()%>/board/list')" value="목록으로">
					</th>
				</tr>
			</table>
		</form>
	</div>
</section>

<%@ include file="/views/common/footer.jsp" %>