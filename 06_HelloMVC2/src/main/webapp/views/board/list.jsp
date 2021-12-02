<%@page import="com.kh.common.util.PageInfo"%>
<%@page import="com.kh.mvc.board.model.vo.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/views/common/header.jsp" %>

<%
	List<Board> list = (List<Board>)request.getAttribute("list");
	PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
%>

<style>
	section#board-list-container{width:600px; margin:0 auto; text-align:center;}
	section#board-list-container h2{margin:10px 0;}
	table#tbl-board{width:100%; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
	table#tbl-board th, table#tbl-board td {border:1px solid; padding: 5px 0; text-align:center;} 
	/*글쓰기버튼*/
	input#btn-add{float:right; margin: 0 0 15px;}
	/*페이지바*/
	div#pageBar{margin-top:10px; text-align:center; background-color:rgba(0, 188, 212, 0.3);}
</style>


<section id="content">
	<h2 align="center">게시판 </h2>
	<% if(loginMember != null) { %>
		<button id="btn-add" onclick="location.href='<%=request.getContextPath()%>/board/write'">글쓰기</button>
	<%} %>
	
	<table id="tbl-board">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>첨부파일</th>
				<th>조회수</th>
			</tr>
			
	   <% if(list == null || list.isEmpty()){%>
			<%-- 게시물이 없는 경우 --%>
			<tr>
				<td colspan="6">
					조회된 게시글이 없습니다.
				</td>
			</tr>	
		<%} else {%>
			<%-- 게시물이 있는 경우 --%>
			<% for(Board board : list) { %>
				<tr>
					<td><%= board.getNo() %></td>
					<td>
						<% String path = request.getContextPath() + "/board/view?boardNo=" + board.getNo(); %>
						<a href="<%=path%>">
							<%= board.getTitle() %>
						</a>
					</td>
					<td><%= board.getWriterId() %></td>
					<td><%= board.getCreateDate() %></td>
					<td>
						<% if(board.getOriginalFileName() != null) { %>
							<img src="<%= request.getContextPath() %>/resources/images/file.png" width="20" height="20"/>
						<%} else { %>
							<span> - </span>
						<%} %>
					</td>
					<td> <%= board.getReadCount() %></td>
				</tr>
			<% } %>
		<%}%>
		
		
	</table>
			<div id="pageBar">
			<!-- 맨 처음으로 -->
			<button onclick="location.href='<%= request.getContextPath() %>/board/list?page=1'">&lt;&lt;</button>
			
			<!-- 이전 페이지로 -->
			<button onclick="location.href='<%= request.getContextPath() %>/board/list?page=<%= pageInfo.getPrvePage() %>'">&lt;</button>

			<!--  10개 페이지 목록 -->
			<% for (int p = pageInfo.getStartPage(); p <= pageInfo.getEndPage(); p++) { %>
				<% if(p == pageInfo.getCurrentPage()) { %>
					<button disabled><%= p %></button>
				<% } else { %>
					<button onclick="location.href='<%= request.getContextPath() %>/board/list?page=<%= p %>'"><%= p %></button>
				<% } %>
			<% } %>
			
			<!-- 다음 페이지로 -->
			<button onclick="location.href='<%= request.getContextPath() %>/board/list?page=<%= pageInfo.getNextPage()%>'">&gt;</button>
			
			<!-- 맨 끝으로 -->
			<button onclick="location.href='<%= request.getContextPath() %>/board/list?page=<%= pageInfo.getMaxPage() %>'">&gt;&gt;</button>
		</div>
	</section>
	
<%@ include file="/views/common/footer.jsp" %>