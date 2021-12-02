<%@page import="com.kh.mvc.board.model.vo.Reply"%>
<%@page import="com.kh.mvc.board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
	Board board = (Board)request.getAttribute("board");
%>

<%@ include file="/views/common/header.jsp" %>

<style>
    section>div#board-write-container{width:600px; margin:0 auto; text-align:center;}
    section>div#board-write-container h2{margin:10px 0;}
    table#tbl-board{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
    table#tbl-board th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
    table#tbl-board td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
    div#comment-container button#btn-insert{width:60px;height:50px; color:white; background-color:#3300FF;position:relative;top:-20px;}
    
    /*댓글테이블*/
    table#tbl-comment{width:580px; margin:0 auto; border-collapse:collapse; clear:both; } 
    table#tbl-comment tr td{border-bottom:1px solid; border-top:1px solid; padding:5px; text-align:left; line-height:120%;}
    table#tbl-comment tr td:first-of-type{padding: 5px 5px 5px 50px;}
    table#tbl-comment tr td:last-of-type {text-align:right; width: 100px;}
    table#tbl-comment button.btn-delete{display:none;}
    table#tbl-comment tr:hover {background:lightgray;}
    table#tbl-comment tr:hover button.btn-delete{display:inline;}
    table#tbl-comment sub.comment-writer {color:navy; font-size:14px}
    table#tbl-comment sub.comment-date {color:tomato; font-size:10px}
</style>


<section>
	<div id="board-write-container">
		<h3>게시판</h3>
		<!-- 게시판 시작 -->
		<table id="tbl-board">
			<tr>
				<th>글번호</th>			
				<td> <%= board.getNo() %> </td>			
			</tr>
			
			<tr>
				<th>제 목</th>			
				<td> <%= board.getTitle() %> </td>			
			</tr>
			
			<tr>
				<th>작성자</th>			
				<td> <%= board.getWriterId() %>  </td>			
			</tr>
			
			<tr>
				<th>조회수</th>			
				<td> <%= board.getReadCount() %>  </td>			
			</tr>
			<tr>
				<th>첨부파일</th>			
				<td> 
					<%
						String name = board.getOriginalFileName();
						String reName = board.getRenamedFileName();
						if(name != null && name.length() > 0 ){
						%>
							<a href="javascript:fileDownload('<%= name %>', '<%=reName%>')">
								<img src="<%= request.getContextPath() %>/resources/images/file.png" width="20" height="20"/>
								<%=name%>			
							</a>
						<% 	
						}
					%>  
				</td>
			</tr>
			<tr>
				<th>내용</th>			
				<td> <%= board.getContent() %>  </td>			
			</tr>
			<tr>
				<td colspan="2"> 
					<%
						if(loginMember != null){
							if(loginMember.getId().equals(board.getWriterId()) || loginMember.getRole().equals("ROLE_ADMIN")){
					%>
						<button type="button" onclick="location.href='<%= request.getContextPath() %>/board/update?boardNo=<%= board.getNo()%>'">수정</button>
						<button type="button" id="btnDelete">삭제</button>
					<% 
							}
						}
					%>
					<button type="button" onclick="location.href='<%= request.getContextPath() %>/board/list'">목록으로</button>
					
				</td>			
			</tr>
		</table>
		<!-- 게시판 종료 -->
		
		<!-- 리플 등록 form 시작 -->
		<div id="comment-container">
		 	<div class="comment-editor">
				<form action="<%= request.getContextPath() %>/board/reply" method="POST">
					<input type="hidden" name="boardNo" value="<%= board.getNo() %>">
	    			<input type="hidden" name="writer" value="<%= loginMember != null ? loginMember.getId() : "" %>">
					<textarea name="content" id="replyContent" cols="55" rows="3"></textarea>
					<button type="submit" id="btn-insert">등록</button>
				</form>
			</div>
		</div>
		<!-- 리플 등록 form 종료 -->
		
		<!-- 리플 리스트 출력 시작 -->
		<table id="tbl-comment">
			<%
				for(Reply reply : board.getReplies()){
			%>
				<tr class="level1">
					<td>
						<sub class="comment-writer"><%= reply.getWriterId() %></sub>
						<sub class="comment-date"><%= reply.getCreateDate() %></sub>
						<br>
						<%= reply.getContent() %>
					</td>
					<td>
						<% if(loginMember != null && loginMember.getId().equals(reply.getWriterId())) { %>
							<!-- 
							<form action="<%= request.getContextPath() %>/board/reply" method="GET">
								<input type="hidden" name="replyNo" value="<%= reply.getNo() %>">
								<button class="btn-delete">삭제</button>
							</form>
							 -->
							<button class="btn-delete" onclick="deleteReply(<%= reply.getNo() %>);">삭제</button>
						<% } %>
					</td>
				</tr>
			<%	
				}
			%>
		</table>
		<!-- 리플 리스트 출력 끝 -->
		
	</div>
</section>

<%@ include file="/views/common/footer.jsp" %>

<script type="text/javascript">
	function fileDownload(originName, reName){
		var url = "<%= request.getContextPath()%>/board/fileDown";
		var oName = encodeURIComponent(originName);
		var rName = encodeURIComponent(reName);
		var requestURL = url + "?oriname=" + oName + "&" + "rename=" + rName;
		console.log(requestURL);
		location.assign(requestURL);
	}
	
	function deleteReply(replyNo){
		var url = "<%= request.getContextPath() %>/board/replydel?replyNo=";
		var requestURL = url + replyNo;
		location.replace(requestURL);
	}
	
	$(document).ready(() => {
		$("#btnDelete").on("click", (e) => {
			if(confirm("정말로 게시글을 삭제 하시겠습니까?")) {
				location.replace("<%= request.getContextPath() %>/board/delete?boardNo=<%= board.getNo()%>");
			}
		});
		
		//$("#replyContent").focus(() => {	
		//});
		
		$("#replyContent").on("focus", (e) => {
			if(<%= loginMember == null %>) {
				alert("로그인 후 이용해주세요!");
				$("#userId").focus();				
			}
		});
	});
</script>
